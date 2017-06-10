package com.vmc.sandbox.payroll.testPreparation

import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.payroll.external.config.DatabaseCleaner
import com.vmc.sandbox.payroll.external.config.ServiceLocator
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Before

abstract class IntegrationTestBase extends ValidationNotificationTestSetup{

    protected DatabaseCleaner databaseCleaner = ServiceLocator.instance.databaseCleaner()
    protected ModelSnapshot model = ServiceLocator.instance.modelSnapshot()

    static{
        ServiceLocator.load(new ServiceLocatorForTest())
    }

    @Before
    public void setUp(){
        super.setUp()
        databaseCleaner.cleanDatabase()
    }

}