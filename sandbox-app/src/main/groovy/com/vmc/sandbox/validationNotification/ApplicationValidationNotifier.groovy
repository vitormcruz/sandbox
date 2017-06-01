package com.vmc.sandbox.validationNotification

import com.vmc.sandbox.validationNotification.imp.SimpleValidationObserverImp

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
        getObservers().put(validationObserver, null)
    }

    static void removeObserver(ValidationObserver validationObserver){
        getObservers().remove(validationObserver)
    }

    static void removeAllObservers(){
        getObservers().clear()
    }

    static void executeNamedValidation(String validationName, Closure validation) {
        startValidation(validationName)
        validation(this)
        finishValidation()
    }

    static void startValidation(String validationName) {
        getObserversIterator().each {it.validationStarted(validationName)}
    }

    static void finishValidation() {
        getObserversIterator().each {it.validationFinished()}
    }

    static void issueMandatoryObligation(Object subject, Map context, String mandatoryValidationName, String error) {
        getObserversIterator().each {it.mandatoryObligationIssued(subject, context, mandatoryValidationName, error)}
    }

    static void issueMandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName) {
        getObserversIterator().each {it.mandatoryObligationComplied(subject, context, mandatoryValidationName)}
    }

    static void issueError(Object subject, Map context, String error) {
        getObserversIterator().each {it.errorIssued(subject, context, error)}
    }

    static void issueError(Object subject, Map context, String instantValidationName, String error) {
        startValidation(subject, instantValidationName)
        getObserversIterator().each {it.errorIssued(error)}
        finishValidation(subject, instantValidationName)
    }

    static Set<ValidationObserver> getObserversIterator() {
        getObservers().keySet()
    }

    static WeakHashMap<ValidationObserver, Void> getObservers() {
        if(!isInitialized()){
            createCurrentListOfListeners()
        }
        return observers.get()
    }

    static boolean isInitialized(){
        return observers != null
    }

    static SimpleValidationObserver getSimpleObserver() {
        def validationObserver = new SimpleValidationObserverImp()
        addObserver(validationObserver)
        return validationObserver
    }
}
