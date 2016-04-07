package sandbox.validation2

interface ValidationObserver {
    void startValidation(String validationName)
    void issueError(String error)
    void finishValidation(String validationName)
}
