package com.vmc.sandbox.validationNotification.imp

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.*
/**
 * Utility to validate mandatory fields
 */
class RequiredValidation {

    private Object subject
    private Map context
    private String attrLabel
    private String errorIfNull
    private Closure resetMandatoryObligation = {
        issueMandatoryObligationComplied(subject, context, attrLabel)
        resetMandatoryObligation = {}
    }

    RequiredValidation(Object subject, Map context, String attrLabel, String errorIfNull) {
        this.subject = subject
        this.context = context
        this.attrLabel = attrLabel
        this.errorIfNull = errorIfNull
        issueMandatoryObligation(subject, context, attrLabel, errorIfNull)
    }

    void set(Object anObject, Closure successClosure, Closure failClosure) {
        resetMandatoryObligation()
        if(anObject == null){
            issueError(attrLabel, context, errorIfNull)
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
