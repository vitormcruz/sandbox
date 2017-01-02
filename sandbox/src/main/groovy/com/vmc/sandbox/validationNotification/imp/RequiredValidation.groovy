package com.vmc.sandbox.validationNotification.imp

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier

/**
 * Utility to validate mandatory fields
 */
class RequiredValidation {

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private String attrLabel
    private String errorIfNull
    private Closure resetMandatoryObligation = {
        notifier.issueMandatoryObligationComplied(attrLabel)
        resetMandatoryObligation = {}
    }

    RequiredValidation(String attrLabel, String errorIfNull) {
        this.attrLabel = attrLabel
        this.errorIfNull = errorIfNull
        notifier.issueMandatoryObligation(attrLabel, errorIfNull)
    }

    void set(Object anObject, Closure successClosure, Closure failClosure) {
        resetMandatoryObligation()
        if(anObject == null){
            notifier.issueError(attrLabel, errorIfNull)
            failClosure()
        }else {
            successClosure()
        }
    }

    void set(Object anObject, Closure successClosure) {
        set(anObject, successClosure, {})
    }

    void set(Object anObject) {
        set(anObject, {}, {})
    }
}
