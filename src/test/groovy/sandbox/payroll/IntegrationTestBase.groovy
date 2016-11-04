package sandbox.payroll

import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import sandbox.payroll.external.config.*
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.ValidationNotificationTestSetup

trait IntegrationTestBase extends ValidationNotificationTestSetup{

    static private SmartFactoryConfig smartFactoryConfig = new SmartFactoryConfig()
    static private HibernateInMemoryConfig hibernateInMemoryConfig = new HibernateInMemoryConfig()
    static private TestConfig testConfig = new TestConfig()
    private DatabaseCleaner databaseCleaner = DatabaseCleaner.smartNewFor(IntegrationTestBase)
    private SpringOpenSessionInTest openSessionInTest = new SpringOpenSessionInTest()

    def static oneTimeConfiguration = {
        smartFactoryConfig.configure()
        hibernateInMemoryConfig.configure()
        testConfig.configure()
        Thread.addShutdownHook {
            smartFactoryConfig.tearDown()
            hibernateInMemoryConfig.tearDown()
            testConfig.tearDown()
        }
        oneTimeConfiguration = {}
    }

    @BeforeClass
    public static void setUpAll(){
        //TODO replicated here from ValidationNotificationTestSetup because groovy does not support static methods on traits properly yet
        if(!ApplicationValidationNotifier.isInitialized()){
            ApplicationValidationNotifier.createCurrentListOfListeners()
            Thread.addShutdownHook {ApplicationValidationNotifier.destroyCurrentListOfListeners()}
        }
        oneTimeConfiguration()
    }

    @Before
    public void setUp(){
        ValidationNotificationTestSetup.super.setUp()
        databaseCleaner.cleanDatabase()
        openSessionInTest.configure()
    }

    @After
    public void tearDown(){
        openSessionInTest.tearDown()
    }

}