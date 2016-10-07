package sandbox.payroll

import org.junit.Before
import org.junit.BeforeClass
import sandbox.payroll.external.config.HibernateInMemoryConfig
import sandbox.payroll.external.config.SmartFactoryConfig
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.ValidationNotificationTestSetup

trait IntegrationTestBase extends ValidationNotificationTestSetup{

    static private SmartFactoryConfig smartFactoryConfig = new SmartFactoryConfig()
    static private HibernateInMemoryConfig hibernateInMemoryConfig = new HibernateInMemoryConfig()

    def static oneTimeConfiguration = {
        smartFactoryConfig.configure()
        hibernateInMemoryConfig.configure()
        Thread.addShutdownHook {
            smartFactoryConfig.tearDown()
            hibernateInMemoryConfig.tearDown()
        }
        oneTimeConfiguration = {}
    }

    @BeforeClass
    public static void setUpAll(){
        //TODO replicated here from ValidationNotificationTestSetup because groovy does not support static methods on trains properly yet
        if(!ApplicationValidationNotifier.isInitialized()){
            ApplicationValidationNotifier.createCurrentListOfListeners()
            Thread.addShutdownHook {ApplicationValidationNotifier.destroyCurrentListOfListeners()}
        }
        oneTimeConfiguration()
    }

    @Before
    public void setUp(){
        /** Groovy looses itself if I don't chain the setUp up. It tells every subclass that setUp does not exists even
         * if ValidationNotificationTestSetup has it **/
        ValidationNotificationTestSetup.super.setUp()
    }

}