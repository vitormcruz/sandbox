package sandbox.payroll

import org.junit.Before
import sandbox.payroll.external.config.HibernateInMemoryConfig
import sandbox.payroll.external.config.SmartFactoryConfig
import sandbox.validationNotification.ValidationNotificationTestSetup

trait IntegrationTestBase extends ValidationNotificationTestSetup{

    static private SmartFactoryConfig smartFactoryConfig = new SmartFactoryConfig()
    static private HibernateInMemoryConfig hibernateInMemoryConfig = new HibernateInMemoryConfig()
    def oneTimeConfiguration = {
        smartFactoryConfig.configure()
        hibernateInMemoryConfig.configure()
        Thread.addShutdownHook {
            smartFactoryConfig.tearDown()
            hibernateInMemoryConfig.tearDown()
        }
        oneTimeConfiguration = {}
    }

    @Before
    public void setUp(){
        ValidationNotificationTestSetup.super.setUp()
        oneTimeConfiguration()
    }

}