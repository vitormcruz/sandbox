package com.vmc.sandbox.validationNotification

interface ValidationObserver {
    void validationStarted(Object subject, Map context, String validationName)
    void mandatoryObligationIssued(Object subject, Map context, String mandatoryValidationName, String error)
    void mandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName)
    void errorIssued(Object subject, Map context, String error)
    void validationFinished(Object subject, Map context)
    Boolean successful()
}
