package sandbox.validator.imp

import org.apache.commons.lang.StringUtils
import org.junit.Test

import static sandbox.validator.imp.EntityExperiment.ValidationUtils.validate

class EntityExperiment implements GroovyInterceptable{

    private methodValidations = [:]
    private ApplicationValidationNotifier errorNotifier = new ApplicationValidationNotifier()

    def name
    def address

    EntityExperiment() {
        methodValidations.put("setName", { name ->
            if(name == null) throw new IllegalArgumentException("name cannot be null")
        })

        methodValidations.put("setAddress", { address ->
            if(address == null) throw new IllegalArgumentException("address cannot be null")
        })

    }

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

    def void doStuff(){

    }

    @Test
    def void sdsdsd(){
        def emp = new EntityExperiment()
        emp.doStuff()
        emp.name = null
        emp.address = "sasas"
        System.out.println(emp.name)
        System.out.println(emp.address)
        assert emp != null
    }

    public static class ValidationUtils{
        private static void validate(methodValidations, String methodName, args) {
            def validation = methodValidations.get(methodName)
            if (validation) {
                validation(*args)
            }
        }
    }

    public static class ApplicationValidationNotifier{

    }


}
