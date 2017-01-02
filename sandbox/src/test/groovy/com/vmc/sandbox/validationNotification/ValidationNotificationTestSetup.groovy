package com.vmc.sandbox.validationNotification

import org.junit.Before
import org.junit.BeforeClass
import com.vmc.sandbox.validationNotification.imp.SimpleValidationObserver

trait ValidationNotificationTestSetup {

    def SimpleValidationObserver validationObserver = new SimpleValidationObserver()

    @BeforeClass
    public static void setUpAll(){
        if(!ApplicationValidationNotifier.isInitialized()){
            ApplicationValidationNotifier.createCurrentListOfListeners()
            Thread.addShutdownHook {ApplicationValidationNotifier.destroyCurrentListOfListeners()}
        }
    }

    @Before
    public void setUp(){
        ApplicationValidationNotifier.removeAllObservers()
        validationObserver = new SimpleValidationObserver()
        ApplicationValidationNotifier.addObserver(validationObserver)
    }

}