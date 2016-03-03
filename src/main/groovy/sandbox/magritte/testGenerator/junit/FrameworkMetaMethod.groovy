package sandbox.magritte.testGenerator.junit
import org.junit.internal.runners.model.ReflectiveCallable
import org.junit.runners.model.FrameworkMethod
import sandbox.magritte.methodGenerator.GeneratedMethod

import java.lang.annotation.Annotation
import java.lang.reflect.Method
/**
 * Class created to hack BlockJUnit4ClassRunner in order to provide groovy dynamic meta methods to be executed by test
 * cases.
 */
class FrameworkMetaMethod extends FrameworkMethod{

    protected GeneratedMethod metaMethod

    FrameworkMetaMethod(Method method) {
        super(method)
    }

    FrameworkMetaMethod(GeneratedMethod metaMethod) {
        super(metaMethod.class.getMethods()[0]) //TODO just pass some object so that FrameworkMethod does not complain.
        this.metaMethod = metaMethod
    }

    @Override
    Object invokeExplosively(Object target, Object... params) throws Throwable {
        return new ReflectiveCallable() {
                    @Override
                    protected Object runReflectiveCall() throws Throwable {
                        return metaMethod.methodBody(params);
                    }
                }.run();
    }

    @Override
    String getName() {
        return metaMethod.getMethodName()
    }

    @Override
    void validatePublicVoidNoArg(boolean isStatic, List<Throwable> errors) {
        //Do nothing, these are generated method, not created by users
    }

    @Override
    void validatePublicVoid(boolean isStatic, List<Throwable> errors) {
        //Do nothing, these are generated method, not created by users
    }

    @Override
    boolean isStatic() {
        return false
    }

    @Override
    boolean isPublic() {
        return true
    }

    @Override
    Class<?> getReturnType() {
        return Object
    }

    @Override
    Class<?> getType() {
        return Object
    }

    @Override
    void validateNoTypeParametersOnArgs(List<Throwable> errors) {
        //Do nothing, these are generated method, not created by users
    }

    @Override
    boolean isShadowedBy(FrameworkMethod other) {
        //Do nothing, these are generated method, not created by users and should not shadow anything
        return false
    }

    private Class<?>[] getParameterTypes() {
        return Collections.emptyList().toArray()
    }

    @Override
    boolean equals(Object obj) {
        if (!FrameworkMetaMethod.class.isInstance(obj)) {
            return false;
        }
        return ((FrameworkMetaMethod) obj).metaMethod.equals(metaMethod);
    }

    @Override
    int hashCode() {
        return metaMethod.hashCode()
    }

    @Override
    Annotation[] getAnnotations() {
        //MetMethod does not have get annotation method
        return new Annotation[0]
    }
}
