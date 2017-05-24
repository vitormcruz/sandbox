package com.vmc.sandbox.payroll.testPreparation

import com.vmc.sandbox.payroll.external.config.DatabaseCleaner
import com.vmc.sandbox.payroll.external.config.InMemoryPersistenceConfig
import com.vmc.sandbox.payroll.external.config.TestConfig
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.detangle.smartfactory.SmartFactory
import org.junit.Before

abstract class IntegrationTestBase extends ValidationNotificationTestSetup{

    private static final smartFactory = SmartFactory.instance()
    private static final InMemoryPersistenceConfig inMemoryPersistenceConfig = new InMemoryPersistenceConfig()
    private static final TestConfig testConfig = new TestConfig()

    static{
        inMemoryPersistenceConfig.configure()
        testConfig.configure()
        Thread.addShutdownHook {
            inMemoryPersistenceConfig.tearDown()
            testConfig.tearDown()
        }
    }

    @Before
    public void setUp(){
        super.setUp()
        getDatabaseCleaner().cleanDatabase()
    }

    public DatabaseCleaner getDatabaseCleaner() {
        return hierarchyAwareInstanceOf(DatabaseCleaner)
    }

    public hierarchyAwareInstanceOf(Class aClass) {
        def databaseCleaner = aClass.smartNewFor(this.getClass())
        if (databaseCleaner == null) {
            databaseCleaner = aClass.smartNewFor(IntegrationTestBase)
        }
        return databaseCleaner
    }

}