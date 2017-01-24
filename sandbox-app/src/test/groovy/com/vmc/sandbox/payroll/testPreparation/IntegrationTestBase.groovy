package com.vmc.sandbox.payroll.testPreparation

import com.vmc.sandbox.payroll.external.config.DatabaseCleaner
import com.vmc.sandbox.payroll.external.config.HibernateInMemoryConfig
import com.vmc.sandbox.payroll.external.config.SpringOpenSessionInTest
import com.vmc.sandbox.payroll.external.config.TestConfig
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.detangle.smartfactory.SmartFactory
import org.junit.After
import org.junit.Before

abstract class IntegrationTestBase extends ValidationNotificationTestSetup{

    private static final smartFactory = SmartFactory.instance()
    private static final HibernateInMemoryConfig hibernateInMemoryConfig = new HibernateInMemoryConfig()
    private static final TestConfig testConfig = new TestConfig()

    static{
        hibernateInMemoryConfig.configure()
        testConfig.configure()
        smartFactory.configurationFor("com.vmc.sandbox.payroll.**").put(SpringOpenSessionInTest, new SpringOpenSessionInTest())
        Thread.addShutdownHook {
            hibernateInMemoryConfig.tearDown()
            testConfig.tearDown()
        }
    }

    @Before
    public void setUp(){
        super.setUp()
        getDatabaseCleaner().cleanDatabase()
        getOpenSessionInTest().configure()
    }

    @After
    public void tearDown(){
        openSessionInTest.tearDown()
    }

    public DatabaseCleaner getDatabaseCleaner() {
        return hierarchyAwareInstanceOf(DatabaseCleaner)
    }

    public SpringOpenSessionInTest getOpenSessionInTest() {
        return hierarchyAwareInstanceOf(SpringOpenSessionInTest)
    }

    public hierarchyAwareInstanceOf(Class aClass) {
        def databaseCleaner = aClass.smartNewFor(this.getClass())
        if (databaseCleaner == null) {
            databaseCleaner = aClass.smartNewFor(IntegrationTestBase)
        }
        return databaseCleaner
    }

}