package com.vmc.sandbox.validationNotification

class ApplicationValidationNotifier {

    private static ThreadLocal<Collection<ValidationObserver>> observers

    ApplicationValidationNotifier() {
    }

    public static createCurrentListOfListeners(){
        observers = new ThreadLocal<Collection<ValidationObserver>>()
        observers.set([])
    }

    public static destroyCurrentListOfListeners(){
        if(observers == null) return
        observers.remove()
        observers = null
    }

    public static addObserver(ValidationObserver validationObserver){
        observers.get().add(validationObserver)
    }

    public static removeObserver(ValidationObserver validationObserver){
        observers.get().remove(validationObserver)
    }

    public static removeAllObservers(){
        observers.get().clear()
    }

    public static boolean isInitialized(){
        return observers != null
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

    public void issueMandatoryObligationComplied(String mandatoryValidationName) {
        observers.get().each {it.issueMandatoryObligationComplied(mandatoryValidationName)}
    }
}
