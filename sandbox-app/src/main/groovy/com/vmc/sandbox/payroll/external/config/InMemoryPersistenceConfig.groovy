package com.vmc.sandbox.payroll.external.config

import com.vmc.sandbox.concurrency.AtomicBlock
import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.concurrency.inMemory.InMemoryAtomicBlock
import com.vmc.sandbox.concurrency.inMemory.InMemoryPersistentModelSnapshot
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.external.persistence.inMemory.repository.CommonInMemoryRepository
import org.detangle.smartfactory.SmartFactory

class InMemoryPersistenceConfig implements Config{
    private smartFactory = SmartFactory.instance()


    //TODO change, don't know how... yet.
    @Override
    public void  configure() {
        def payRollConfiguration = smartFactory.configurationFor("com.vmc.sandbox.payroll.**")
        def concurrencyConfiguration = smartFactory.configurationFor("com.vmc.sandbox.concurrency.**")

        payRollConfiguration.put(AtomicBlock, new InMemoryAtomicBlock())
        concurrencyConfiguration.put(AtomicBlock, new InMemoryAtomicBlock())
        def employeeRepository = new CommonInMemoryRepository<Employee>()
        payRollConfiguration.put(CommonInMemoryRepository, employeeRepository)
        payRollConfiguration.put(ModelSnapshot, {
            def modelSnapshot = new InMemoryPersistentModelSnapshot()
            modelSnapshot.add(employeeRepository)
            return modelSnapshot
        }())

    }

    @Override
    public void tearDown() {

    }
}
