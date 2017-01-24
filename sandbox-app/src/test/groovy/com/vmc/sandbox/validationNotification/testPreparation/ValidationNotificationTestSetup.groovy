package com.vmc.sandbox.validationNotification.testPreparation

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.ValidationObserver
import com.vmc.sandbox.validationNotification.imp.SimpleValidationObserver
import org.junit.Before

abstract class ValidationNotificationTestSetup {

    protected SimpleValidationObserver validationObserver = new SimpleValidationObserver()

    static{
        if(!ApplicationValidationNotifier.isInitialized()){
            ApplicationValidationNotifier.createCurrentListOfListeners()
            Thread.addShutdownHook {ApplicationValidationNotifier.destroyCurrentListOfListeners()}
        }
    }

    public ValidationObserver getValidationObserver(){
        return validationObserver
    }

    @Before
    public void setUp(){
        ApplicationValidationNotifier.removeAllObservers()
        validationObserver = new SimpleValidationObserver()
        ApplicationValidationNotifier.addObserver(validationObserver)
    }

}