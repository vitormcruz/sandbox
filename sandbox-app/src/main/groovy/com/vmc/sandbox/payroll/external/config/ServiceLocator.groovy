package com.vmc.sandbox.payroll.external.config

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.vmc.sandbox.concurrency.AtomicBlock
import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.concurrency.inMemory.InMemoryAtomicBlock
import com.vmc.sandbox.concurrency.inMemory.InMemoryPersistentModelSnapshot
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.Repository
import com.vmc.sandbox.payroll.external.persistence.inMemory.repository.CommonInMemoryRepository

class ServiceLocator {

    private static myself = new ServiceLocator()

    private ObjectMapper mapper = new ObjectMapper().configure(MapperFeature.AUTO_DETECT_FIELDS, false)
    AtomicBlock atomicBlock = new InMemoryAtomicBlock()
    private ModelSnapshot modelSnapshot = new InMemoryPersistentModelSnapshot(atomicBlock())
    private Repository<Employee> employeeRepository = new CommonInMemoryRepository<Employee>()

    protected ServiceLocator(){
        modelSnapshot.add(employeeRepository)
    }

    static getInstance(){
        return myself
    }

    /**
     * Load another service locator instance
     */
    static load(ServiceLocator serviceLocator){
        myself = serviceLocator
    }

    ObjectMapper mapper() {
        return mapper
    }

    ModelSnapshot modelSnapshot(){
        return modelSnapshot
    }

    AtomicBlock atomicBlock(){
        return atomicBlock
    }

    Repository<Employee> employeeRepository(){
        return employeeRepository
    }
}
