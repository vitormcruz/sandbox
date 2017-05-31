package com.vmc.sandbox.validationNotification.testPreparation

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.imp.SimpleValidationObserverImp
import org.junit.Before

abstract class ValidationNotificationTestSetup {

    protected SimpleValidationObserverImp validationObserver = new SimpleValidationObserverImp()

    public SimpleValidationObserverImp getValidationObserver(){
        return validationObserver
    }

    @Before
    public void setUp(){
        ApplicationValidationNotifier.createCurrentListOfListeners()
        validationObserver = new SimpleValidationObserverImp()
        ApplicationValidationNotifier.addObserver(validationObserver)
    }

}