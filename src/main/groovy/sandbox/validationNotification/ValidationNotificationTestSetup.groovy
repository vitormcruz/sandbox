package sandbox.validationNotification

import org.junit.Before
import sandbox.validationNotification.imp.SimpleValidationObserver

trait ValidationNotificationTestSetup {

    def SimpleValidationObserver validationObserver = new SimpleValidationObserver()

    @Before
    public void setUp(){
        if(!ApplicationValidationNotifier.isInitialized()){
            ApplicationValidationNotifier.createCurrentListOfListeners()
            Thread.addShutdownHook {ApplicationValidationNotifier.destroyCurrentListOfListeners()}
        }

        ApplicationValidationNotifier.removeAllObservers()
        validationObserver = new SimpleValidationObserver()
        ApplicationValidationNotifier.addObserver(validationObserver)
    }

}