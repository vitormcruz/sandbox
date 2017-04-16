package com.vmc.sandbox.payroll.external.config

import com.vmc.sandbox.payroll.payment.type.Monthly
import com.vmc.sandbox.payroll.external.simpleConverter.SimpleObjectMapping
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import org.detangle.smartfactory.SmartFactory

class SpringMVCConfig implements Config {

    private smartFactory = SmartFactory.instance()

    @Override
    public void  configure() {
        generalAppConfig()
    }

    private void generalAppConfig() {
        def globalConfiguration = smartFactory.configurationFor("com.vmc.sandbox.payroll.**")
        SimpleObjectMapping objectMapping = new SimpleObjectMapping()
        def objectMappingForBuilder = objectMapping.getObjectMappingFor(GenericBuilder)
        dynamicMappingForEmployee(objectMappingForBuilder)
        globalConfiguration.put(SimpleObjectMapping, objectMapping)
    }

    private Object dynamicMappingForEmployee(LinkedHashMap objectMappingForBuilder) {
        objectMappingForBuilder.put("paymentMethod", { employeeBuilder, paymentMethodMap ->
            employeeBuilder.setPaymentType(new Monthly(Integer.valueOf(paymentMethodMap.get("salary"))))
        })
    }

    @Override
    public void tearDown() {
    }
}
