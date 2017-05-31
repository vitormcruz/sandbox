package com.vmc.sandbox.validationNotification.constructor

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.SimpleValidationObserver
import com.vmc.sandbox.validationNotification.builder.ConstructionValidationFailedException
import org.apache.commons.lang.StringUtils

class ObjectValidationConstructorExtensions {

    public static Object validateConstruction(Object anObject, Closure construction){
        SimpleValidationObserver simpleValidationObserver = ApplicationValidationNotifier.getSimpleObserver()
        construction()
        if(!simpleValidationObserver.successful()) throw new ConstructionValidationFailedException(StringUtils.join(simpleValidationObserver.errors, ", "))
    }
}
