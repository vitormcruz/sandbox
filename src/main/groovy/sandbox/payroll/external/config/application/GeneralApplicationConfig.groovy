package sandbox.payroll.external.config.application

import sandbox.payroll.imp.Salary
import sandbox.sandboxapp.Config
import sandbox.simpleConverter.SimpleObjectMapping
import sandbox.validationNotification.builder.GenericValidationNotifierBuilder

class GeneralApplicationConfig implements Config {

    @Override
    public void  configure() {
        //TODO change to payroll
        def globalConfiguration = smartFactory.configurationFor("**")
        SimpleObjectMapping objectMapping = new SimpleObjectMapping()
        def objectMappingForBuilder = objectMapping.getObjectMappingFor(GenericValidationNotifierBuilder)
        dynamicMappingForEmployee(objectMappingForBuilder)
        globalConfiguration.put(SimpleObjectMapping, objectMapping)
    }

    private Object dynamicMappingForEmployee(LinkedHashMap objectMappingForBuilder) {
        objectMappingForBuilder.put("paymentMethod", { employeeBuilder, paymentMethodMap ->
            employeeBuilder.setPaymentMethod(new Salary(Integer.valueOf(paymentMethodMap.get("salary"))))
        })
    }

    @Override
    public void tearDown() {
    }
}
