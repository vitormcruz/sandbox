package com.vmc.sandbox.validationNotification

class ApplicationValidationNotifier {

    private static ThreadLocal<WeakHashMap<ValidationObserver, Void>> observers

    private ApplicationValidationNotifier() {
    }

    static void createCurrentListOfListeners(){
        observers = new ThreadLocal<Collection<ValidationObserver>>()
        observers.set(new WeakHashMap())
    }

    static void destroyCurrentListOfListeners(){
        if(observers == null) return
        observers.remove()
        observers = null
    }

    static void addObserver(ValidationObserver validationObserver){
        observers.get().put(validationObserver, null)
    }

    static void removeObserver(ValidationObserver validationObserver){
        observers.get().remove(validationObserver)
    }

    static void removeAllObservers(){
        observers.get().clear()
    }

    static boolean isInitialized(){
        return observers != null
    }

    static void executeNamedValidation(Object subject, String validationName, Closure validation) {
        startValidation(subject, validationName)
        validation(this)
        finishValidation(subject, validationName)
    }

    static void startValidation(Object subject, String validationName) {
        getObserversIterator().each {it.startValidation(subject, validationName)}
    }

    static private void finishValidation(Object subject, String validationName) {
        getObserversIterator().each {it.finishValidation(subject, validationName)}
    }

    static public void issueMandatoryObligation(Object subject, String mandatoryValidationName, String error) {
        getObserversIterator().each {it.issueMandatoryObligation(subject, mandatoryValidationName, error)}
    }

    static public void issueMandatoryObligationComplied(Object subject, String mandatoryValidationName) {
        getObserversIterator().each {it.issueMandatoryObligationComplied(subject, mandatoryValidationName)}
    }

    static public void issueError(Object subject, String error) {
        getObserversIterator().each {it.issueError(subject, error)}
    }

    static public void issueError(Object subject, String instantValidationName, String error) {
        startValidation(subject, instantValidationName)
        getObserversIterator().each {it.issueError(error)}
        finishValidation(subject, instantValidationName)
    }

    static public Set<ValidationObserver> getObserversIterator() {
        observers.get().keySet()
    }
}
