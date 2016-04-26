package sandbox.validationNotification

class ApplicationValidationNotifier {

    private static ThreadLocal<Collection<ValidationObserver>> observers

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

    public void issueError(String error) {
        observers.get().each {it.issueError(error)}
    }

}
