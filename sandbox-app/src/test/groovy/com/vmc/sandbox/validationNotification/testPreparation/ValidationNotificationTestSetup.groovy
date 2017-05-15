package com.vmc.sandbox.validationNotification.testPreparation

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.imp.SimpleValidationObserver
import org.junit.Before

abstract class ValidationNotificationTestSetup {

    protected SimpleValidationObserver validationObserver = new SimpleValidationObserver()

    public SimpleValidationObserver getValidationObserver(){
        return validationObserver
    }

    @Before
    public void setUp(){
        ApplicationValidationNotifier.createCurrentListOfListeners()
        validationObserver = new SimpleValidationObserver()
        ApplicationValidationNotifier.addObserver(validationObserver)
    }

}