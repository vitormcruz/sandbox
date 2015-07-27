package sandbox.magritte.testGenerator.junit

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.junit.internal.runners.model.ReflectiveCallable
import org.junit.runners.model.FrameworkMethod

import java.lang.annotation.Annotation
import java.lang.reflect.Method
/**
 */
class FrameworkClosureMethod extends FrameworkMethod {

    protected Closure closureMethod
    protected String methodName

    FrameworkClosureMethod(Method method) {
        super(method)
    }

    FrameworkClosureMethod(String methodName, Closure closureMethod) {
        super(this.getClass().getMethods()[0]) //TODO just pass some object so that FrameworkMethod does not complain.
        this.methodName = methodName
        this.closureMethod = closureMethod
    }

    @Override
    Object invokeExplosively(Object target, Object... params) throws Throwable {
        return new ReflectiveCallable() {
            @Override
            protected Object runReflectiveCall() throws Throwable {
                return target.executeClosureValidation(closureMethod, params)
            }
        }.run();
    }

    @Override
    String getName() {
        return methodName
    }

    @Override
    void validatePublicVoidNoArg(boolean isStatic, List<Throwable> errors) {
        //Do nothing. Validation method can have parameters.
    }

    @Override
    void validatePublicVoid(boolean isStatic, List<Throwable> errors) {
        //Do nothing. This do not apply to closures
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
        return Object;
    }

    @Override
    Class<?> getType() {
        return Object;
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
        return closureMethod.getParameterTypes();
    }

    @Override
    boolean equals(Object obj) {
        if (!FrameworkMetaMethod.class.isInstance(obj)) {
            return false;
        }
        return new EqualsBuilder().append(getName(), obj.getName()).isEquals();
    }

    @Override
    int hashCode() {
        return new HashCodeBuilder().append(getName()).toHashCode()
    }

    @Override
    Annotation[] getAnnotations() {
        //Closures does not have annotations
        return new Annotation[0]
    }
}