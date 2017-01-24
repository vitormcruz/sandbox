package com.vmc.sandbox.validationNotification.imp

import com.vmc.sandbox.validationNotification.ValidationObserver

class SimpleValidationObserver implements ValidationObserver{

    def errors = []
    def mandatoryObligation = [:]

    @Override
    void startValidation(String validationName) {

    }

    @Override
    void issueMandatoryObligation(String mandatoryValidationName, String error) {
        mandatoryObligation.put(mandatoryValidationName, error)
    }

    @Override
    void issueMandatoryObligationComplied(String mandatoryValidationName) {
        mandatoryObligation.remove(mandatoryValidationName)
    }

    @Override
    void issueError(String error) {
        errors.add(error)
    }

    @Override
    void finishValidation(String validationName) {

    }

    @Override
    Boolean successful() {
        return errors.isEmpty()
    }

    def getErrors() {
        return errors + mandatoryObligation.collect {it.value}
    }
}
