package sandbox.validationNotification.generation

import org.apache.commons.lang.StringUtils

abstract class ValidationGeneratorTrait implements GroovyInterceptable{

    private GeneratedValidations generatedValidations

    ValidationGeneratorTrait() {
        this.generatedValidations = new GeneratedValidations(this)
    }

    Object invokeMethod(String name, Object args) {
        this.generatedValidations?.validate(name, args)
        return metaClass.getMetaMethod(name, args).invoke(this, args)
    }

    Object getProperty(String property) {
        generatedValidations?.validate("get" + StringUtils.capitalize(property), [] as Object[])
        return metaClass.getMetaProperty(property).getProperty(this)
    }

    void setProperty(String property, Object newValue) {
        generatedValidations?.validate("set" + StringUtils.capitalize(property), [newValue] as Object[])
        metaClass.getMetaProperty(property).setProperty(this, newValue)
    }

}
