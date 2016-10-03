package sandbox.payroll.external.config

import sandbox.concurrency.ModelSnapshot
import sandbox.payroll.EmployeeRepository
import sandbox.payroll.external.interfaceAdapter.persistence.hibernate.HibernatePersistentModelSnapshot
import sandbox.payroll.external.interfaceAdapter.persistence.hibernate.repository.HibernateEmployeeRepository
import sandbox.payroll.imp.Salary
import sandbox.sevletContextConfig.Config
import sandbox.simpleConverter.SimpleObjectMapping
import sandbox.smartfactory.SmartFactory
import sandbox.validationNotification.builder.GenericValidationNotifierBuilder

class SmartFactoryConfig implements Config {

    private smartFactory = SmartFactory.instance()

    @Override
    public void  configure() {
        generalAppConfig()
        persistenceConfig()
    }

    private void generalAppConfig() {
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

    private void persistenceConfig() {
        //TODO using sandbox.** causes an error that must be fixed at smart factory
        def sandBoxConfiguration = smartFactory.configurationFor("sandbox.payroll.**")
        def employeeRepository = new HibernateEmployeeRepository()
        sandBoxConfiguration.put(EmployeeRepository, employeeRepository)
        sandBoxConfiguration.put(ModelSnapshot, {
            def modelSnapshot = new HibernatePersistentModelSnapshot()
            modelSnapshot.add(employeeRepository)
            return modelSnapshot
        }())
    }

    @Override
    public void tearDown() {
    }
}
