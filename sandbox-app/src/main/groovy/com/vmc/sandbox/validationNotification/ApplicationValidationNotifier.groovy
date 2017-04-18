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

    static void executeNamedValidation(Object subject, Map context, String validationName, Closure validation) {
        startValidation(subject, context, validationName)
        validation(this)
        finishValidation(subject, context, validationName)
    }

    static void startValidation(Object subject, Map context, String validationName) {
        getObserversIterator().each {it.startValidation(subject, context, validationName)}
    }

    static private void finishValidation(Object subject, Map context, String validationName) {
        getObserversIterator().each {it.finishValidation(subject, context, validationName)}
    }

    static public void issueMandatoryObligation(Object subject, Map context, String mandatoryValidationName, String error) {
        getObserversIterator().each {it.issueMandatoryObligation(subject, context, mandatoryValidationName, error)}
    }

    static public void issueMandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName) {
        getObserversIterator().each {it.issueMandatoryObligationComplied(subject, context, mandatoryValidationName)}
    }

    static public void issueError(Object subject, Map context, String error) {
        getObserversIterator().each {it.issueError(subject, context, error)}
    }

    static public void issueError(Object subject, Map context, String instantValidationName, String error) {
        startValidation(subject, instantValidationName)
        getObserversIterator().each {it.issueError(error)}
        finishValidation(subject, instantValidationName)
    }

    static public Set<ValidationObserver> getObserversIterator() {
        observers.get().keySet()
    }
}
