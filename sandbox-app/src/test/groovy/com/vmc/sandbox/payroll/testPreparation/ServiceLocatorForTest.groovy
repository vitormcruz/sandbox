package com.vmc.sandbox.payroll.testPreparation

import com.vmc.sandbox.payroll.external.config.DatabaseCleaner
import com.vmc.sandbox.payroll.external.config.ServiceLocator

class ServiceLocatorForTest extends ServiceLocator{
    private DatabaseCleaner databaseCleaner = new DatabaseCleaner(modelSnapshot(), employeeRepository())

    ServiceLocatorForTest() {
    }

    DatabaseCleaner databaseCleaner() {
        return databaseCleaner
    }
}
