package com.vmc.sandbox.payroll.external.config

import com.vmc.sandbox.payroll.payment.type.Monthly
import com.vmc.sandbox.simpleConverter.SimpleObjectMapping
import com.vmc.smartfactory.SmartFactory
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

class SmartFactoryConfig implements Config {

    private smartFactory = SmartFactory.instance()

    @Override
    public void  configure() {
        generalAppConfig()
    }

    private void generalAppConfig() {
        //TODO change to payroll
        def globalConfiguration = smartFactory.configurationFor("**")
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
