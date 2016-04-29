package sandbox.validationNotification

interface ValidationObserver {
    void startValidation(String validationName)
    void issueError(String error)
    void finishValidation(String validationName)
    Boolean successful()
}
