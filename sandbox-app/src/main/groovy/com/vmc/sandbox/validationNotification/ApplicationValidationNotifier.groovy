package com.vmc.sandbox.validationNotification

class ApplicationValidationNotifier {

    private static ThreadLocal<WeakHashMap<ValidationObserver, Void>> observers

    ApplicationValidationNotifier() {
    }

    public static createCurrentListOfListeners(){
        observers = new ThreadLocal<Collection<ValidationObserver>>()
        observers.set(new WeakHashMap())
    }

    public static destroyCurrentListOfListeners(){
        if(observers == null) return
        observers.remove()
        observers = null
    }

    public static addObserver(ValidationObserver validationObserver){
        observers.get().put(validationObserver, null)
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

    void executeNamedValidation(Object subject, String validationName, Closure validation) {
        startValidation(subject, validationName)
        validation(this)
        finishValidation(subject, validationName)
    }

    private void startValidation(Object subject, String validationName) {
        getObserversIterator().each {it.startValidation(subject, validationName)}
    }

    private void finishValidation(Object subject, String validationName) {
        getObserversIterator().each {it.finishValidation(subject, validationName)}
    }

    public void issueMandatoryObligation(Object subject, String mandatoryValidationName, String error) {
        getObserversIterator().each {it.issueMandatoryObligation(subject, mandatoryValidationName, error)}
    }

    public void issueMandatoryObligationComplied(Object subject, String mandatoryValidationName) {
        getObserversIterator().each {it.issueMandatoryObligationComplied(subject, mandatoryValidationName)}
    }

    public void issueError(Object subject, String error) {
        getObserversIterator().each {it.issueError(subject, error)}
    }

    public void issueError(Object subject, String instantValidationName, String error) {
        startValidation(subject, instantValidationName)
        getObserversIterator().each {it.issueError(error)}
        finishValidation(subject, instantValidationName)
    }

    public Set<ValidationObserver> getObserversIterator() {
        observers.get().keySet()
    }
}
