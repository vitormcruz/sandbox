package com.vmc.sandbox.validationNotification

interface ValidationObserver {
    void startValidation(Object subject, String validationName)
    void issueMandatoryObligation(Object subject, String mandatoryValidationName, String error)
    void issueMandatoryObligationComplied(Object subject, String mandatoryValidationName)
    void issueError(Object subject, String error)
    void finishValidation(Object subject, String validationName)
    Boolean successful()
}
