package sandbox.payroll.external.config.persistence

import sandbox.concurrency.ModelSnapshot
import sandbox.payroll.EmployeeRepository
import sandbox.payroll.external.interfaceAdapter.persistence.hibernate.HibernatePersistentModelSnapshot
import sandbox.payroll.external.interfaceAdapter.persistence.hibernate.repository.HibernateEmployeeRepository
import sandbox.sandboxapp.Config
import sandbox.smartfactory.SmartFactory

class PersistenceConfig implements Config {

    private smartFactory = SmartFactory.instance()

    @Override
    public void  configure() {
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
    public void  tearDown() {
    }
}
