package com.vmc.sandbox.validationNotification.imp

import com.vmc.sandbox.validationNotification.ValidationObserver

class SimpleValidationObserver implements ValidationObserver{

    def errors = []
    def mandatoryObligation = [:]

    @Override
    void startValidation(Object subject, Map context, String validationName) {

    }

    @Override
    void issueMandatoryObligation(Object subject, Map context, String mandatoryValidationName, String error) {
        mandatoryObligation.put(mandatoryValidationName, error)
    }

    @Override
    void issueMandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName) {
        mandatoryObligation.remove(mandatoryValidationName)
    }

    @Override
    void issueError(Object subject, Map context, String error) {
        errors.add(error)
    }

    @Override
    void finishValidation(Object subject, Map context, String validationName) {

    }

    @Override
    Boolean successful() {
        return errors.isEmpty()
    }

    def getErrors() {
        return errors + mandatoryObligation.collect {it.value}
    }
}
