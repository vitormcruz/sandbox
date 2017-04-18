package com.vmc.sandbox.validationNotification

interface ValidationObserver {
    void startValidation(Object subject, Map context, String validationName)
    void issueMandatoryObligation(Object subject, Map context, String mandatoryValidationName, String error)
    void issueMandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName)
    void issueError(Object subject, Map context, String error)
    void finishValidation(Object subject, Map context, String validationName)
    Boolean successful()
}
