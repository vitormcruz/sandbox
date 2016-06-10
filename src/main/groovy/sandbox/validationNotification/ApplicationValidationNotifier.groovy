package sandbox.validationNotification

class ApplicationValidationNotifier {

    private static ThreadLocal<Collection<ValidationObserver>> observers

    ApplicationValidationNotifier() {
    }

    public static createCurrentListOfListeners(){
        observers = new ThreadLocal<Collection<ValidationObserver>>()
        observers.set([])
    }

    public static destroyCurrentListOfListeners(){
        observers.remove()
        observers = null
    }

    public static addObserver(ValidationObserver validationObserver){
        observers.get().add(validationObserver)
    }

    public static removeObserver(ValidationObserver validationObserver){
        observers.get().remove(validationObserver)
    }

    void executeNamedValidation(String validationName, Closure validation) {
        startValidation(validationName)
        validation(this)
        finishValidation(validationName)
    }

    private void startValidation(String validationName) {
        observers.get().each {it.startValidation(validationName)}
    }

    private void finishValidation(String validationName) {
        observers.get().each {it.finishValidation(validationName)}
    }

    public void issueMandatoryObligation(String mandatoryValidationName, String error) {
        observers.get().each {it.issueMandatoryObligation(mandatoryValidationName, error)}
    }

    public void issueError(String error) {
        observers.get().each {it.issueError(error)}
    }

    public void issueError(String instantValidationName, String error) {
        startValidation(instantValidationName)
        observers.get().each {it.issueError(error)}
        finishValidation(instantValidationName)
    }

    def getValidationSession() {
        def session = new ValidationSession()
        addObserver(session)
        return session
    }

    public void issueMandatoryObligationComplied(String mandatoryValidationName) {
        observers.get().each {it.issueMandatoryObligationComplied(mandatoryValidationName)}
    }

    public static class ValidationSession implements ValidationObserver{
        private Boolean successful = true

        @Override
        void startValidation(String validationName) {

        }

        @Override
        void issueMandatoryObligation(String mandatoryValidationName, String error) {

        }

        @Override
        void issueMandatoryObligationComplied(String mandatoryValidationName) {

        }

        @Override
        void issueError(String error) {
            successful = false
        }

        @Override
        void finishValidation(String validationName) {

        }

        @Override
        public Boolean successful(){
            return successful
        }

        public void stopSession() {
            removeObserver(this)
        }
    }
}
