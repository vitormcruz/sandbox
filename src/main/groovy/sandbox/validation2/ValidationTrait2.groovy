package sandbox.validation2

import org.apache.commons.lang.StringUtils

import static sandbox.validation2.ValidationTrait2.ValidationUtils.validate

class ValidationTrait2 implements GroovyInterceptable{

    private methodValidations = [:]
    protected ApplicationValidationNotifier validationNotifier = new ApplicationValidationNotifier()

    @Override
    Object invokeMethod(String name, Object args) {
        validate(methodValidations, name, args)
        return metaClass.getMetaMethod(name, args).invoke(this, args)
    }

    @Override
    Object getProperty(String property) {
        validate(methodValidations, "get" + StringUtils.capitalize(property), [] as Object[])
        return metaClass.getMetaProperty(property).getProperty(this)
    }

    @Override
    void setProperty(String property, Object newValue) {
        validate(methodValidations, "set" + StringUtils.capitalize(property), [newValue] as Object[])
        metaClass.getMetaProperty(property).setProperty(this, newValue)
    }

    public static class ValidationUtils{
        private static void validate(methodValidations, String methodName, args) {
            def validation = methodValidations.get(methodName)
            if (validation) {
                validation(*args)
            }
        }
    }

}
