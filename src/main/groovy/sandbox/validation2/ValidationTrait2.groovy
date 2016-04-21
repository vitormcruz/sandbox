package sandbox.validation2

import org.apache.commons.lang.StringUtils

abstract class ValidationTrait2 implements GroovyInterceptable{

    private GeneratedValidations generatedValidations = new GeneratedValidations(this)
    protected ApplicationValidationNotifier validationNotifier = new ApplicationValidationNotifier()

    Object invokeMethod(String name, Object args) {
        this.generatedValidations.validate(name, args)
        return metaClass.getMetaMethod(name, args).invoke(this, args)
    }

    Object getProperty(String property) {
        generatedValidations.validate("get" + StringUtils.capitalize(property), [] as Object[])
        return metaClass.getMetaProperty(property).getProperty(this)
    }

    void setProperty(String property, Object newValue) {
        generatedValidations.validate("set" + StringUtils.capitalize(property), [newValue] as Object[])
        metaClass.getMetaProperty(property).setProperty(this, newValue)
    }

}
