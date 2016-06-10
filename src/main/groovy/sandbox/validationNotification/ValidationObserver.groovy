package sandbox.validationNotification

interface ValidationObserver {
    void startValidation(String validationName)
    void issueMandatoryObligation(String mandatoryValidationName, String error)
    void issueMandatoryObligationComplied(String mandatoryValidationName)
    void issueError(String error)
    void finishValidation(String validationName)
    Boolean successful()
}
