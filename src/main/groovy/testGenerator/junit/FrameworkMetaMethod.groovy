package testGenerator.junit
import org.junit.internal.runners.model.ReflectiveCallable
import org.junit.runners.model.FrameworkMethod

import java.lang.annotation.Annotation
import java.lang.reflect.Method
import java.lang.reflect.Modifier
/**
 * Class created to hack BlockJUnit4ClassRunner in order to provide groovy dynamic meta methods to be executed by test
 * cases. The BlockJUnit4ClassRunner is very inflexible around that since it uses FrameworkMethod instead of
 * FrameworkMember or some interface.
 */
class FrameworkMetaMethod extends FrameworkMethod{

    protected MetaMethod metaMethod

    FrameworkMetaMethod(Method method) {
        super(method)
    }

    FrameworkMetaMethod(MetaMethod metaMethod) {
        super(null)
        this.metaMethod = metaMethod
    }

    @Override
    Object invokeExplosively(Object target, Object... params) throws Throwable {
        return new ReflectiveCallable() {
                    @Override
                    protected Object runReflectiveCall() throws Throwable {
                        return metaMethod.invoke(target, params);
                    }
                }.run();
    }

    @Override
    String getName() {
        return metaMethod.getName()
    }

    @Override
    void validatePublicVoidNoArg(boolean isStatic, List<Throwable> errors) {
        validatePublicVoid(isStatic, errors);
        if (metaMethod.getParameterTypes().length != 0) {
            errors.add(new Exception("Method " + metaMethod.getName() + " should have no parameters"));
        }
    }

    @Override
    void validatePublicVoid(boolean isStatic, List<Throwable> errors) {
        if (Modifier.isStatic(metaMethod.getModifiers()) != isStatic) {
            String state = isStatic ? "should" : "should not";
            errors.add(new Exception("Method " + metaMethod.getName() + "() " + state + " be static"));
        }
        if (!Modifier.isPublic(metaMethod.getDeclaringClass().getModifiers())) {
            errors.add(new Exception("Class " + metaMethod.getDeclaringClass().getName() + " should be public"));
        }
        if (!Modifier.isPublic(metaMethod.getModifiers())) {
            errors.add(new Exception("Method " + metaMethod.getName() + "() should be public"));
        }
        if (method.getReturnType() != Void.TYPE) {
            errors.add(new Exception("Method " + metaMethod.getName() + "() should be void"));
        }
    }

    @Override
    boolean isStatic() {
        return Modifier.isStatic(metaMethod.getModifiers());
    }

    @Override
    boolean isPublic() {
        return Modifier.isStatic(metaMethod.getModifiers());
    }

    @Override
    Class<?> getReturnType() {
        return metaMethod.getReturnType();
    }

    @Override
    Class<?> getType() {
        return metaMethod.getReturnType();
    }

    @Override
    void validateNoTypeParametersOnArgs(List<Throwable> errors) {
        //TODO don't know what to do here.
    }

    @Override
    boolean isShadowedBy(FrameworkMethod other) {
        if (!other.getName().equals(getName())) {
            return false;
        }
        if (other.getParameterTypes().length != getParameterTypes().length) {
            return false;
        }
        for (int i = 0; i < other.getParameterTypes().length; i++) {
            if (!other.getParameterTypes()[i].equals(getParameterTypes()[i])) {
                return false;
            }
        }
        return true;
    }

    private Class<?>[] getParameterTypes() {
        return metaMethod.getParameterTypes();
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
        //MetMethod does not have get annottion method
        return new Annotation[0]
    }

    @Override
    def <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        //MetMethod does not have get annottion method
        return null
    }
}
