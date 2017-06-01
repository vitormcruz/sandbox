package com.vmc.sandbox.validationNotification.imp

import com.vmc.sandbox.validationNotification.SimpleValidationObserver
import org.apache.commons.lang.StringUtils

class SimpleValidationObserverImp implements SimpleValidationObserver{

    def errors = []
    def mandatoryObligation = [:]

    @Override
    void validationStarted(String validationName) {

    }

    @Override
    void mandatoryObligationIssued(Object subject, Map context, String mandatoryValidationName, String error) {
        mandatoryObligation.put(mandatoryValidationName, error)
    }

    @Override
    void mandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName) {
        mandatoryObligation.remove(mandatoryValidationName)
    }

    @Override
    void errorIssued(Object subject, Map context, String error) {
        errors.add(error)
    }

    @Override
    void validationFinished() {

    }

    @Override
    Boolean successful() {
        return errors.isEmpty()
    }

    @Override
    def getErrors() {
        return errors + mandatoryObligation.collect {it.value}
    }

    def getCommaSeparatedErrors(){
        return StringUtils.join(getErrors(), ", ")
    }
}
