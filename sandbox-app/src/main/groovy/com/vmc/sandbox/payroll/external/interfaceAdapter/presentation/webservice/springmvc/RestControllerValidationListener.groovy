package com.vmc.sandbox.payroll.external.interfaceAdapter.presentation.webservice.springmvc

import org.springframework.http.ResponseEntity
import com.vmc.sandbox.validationNotification.ValidationObserver

public class RestControllerValidationListener implements ValidationObserver{

    private Map<String, Collection> errorsByValidation
    private currentErrors
    private mandatoryObligations = [:]
    private Boolean successful = true
    def private generateResponseStrategy
    def private issueErrorStrategy
    def body

    RestControllerValidationListener() {
        currentErrors = new ArrayList()
        errorsByValidation = [null: currentErrors]
        generateResponseStrategy = responseOkStrategy
        issueErrorStrategy = issueFirstErrorStrategy
    }

    public Map<String, Collection> getErrorsByValidation(){
        return errorsByValidation.findAll {!it.value.isEmpty()}
    }

    @Override
    void startValidation(String validationName) {
        currentErrors = new ArrayList()
        errorsByValidation.put(validationName, currentErrors)
    }

    @Override
    void issueMandatoryObligation(String mandatoryValidationName, String error) {
        mandatoryObligations.put(mandatoryValidationName, error)
    }

    @Override
    void issueMandatoryObligationComplied(String mandatoryValidationName) {
        mandatoryObligations.remove(mandatoryValidationName)
    }

    @Override
    void issueError(String error) {
        issueErrorStrategy(error)
    }

    def private issueFirstErrorStrategy = { error ->
        issueErrorOnly(error)
        generateResponseStrategy = responseFailStrategy
        successful = false
        issueFirstErrorStrategy = issueErrorOnly
    }

    def private issueErrorOnly = { error ->
        currentErrors.add(error)
    }

    @Override
    void finishValidation(String validationName) {
        currentErrors = errorsByValidation.get(null)
    }

    @Override
    Boolean successful() {
        return successful && mandatoryObligations.isEmpty()
    }

    public generateResponse(){
        mandatoryObligations.each {issueError(it.value)}
        mandatoryObligations.clear()
        return generateResponseStrategy()
    }

    def private responseOkStrategy = {
        return ResponseEntity.ok(body)
    }

    def private responseFailStrategy = {
        return ResponseEntity.badRequest().body(errorsByValidation)
    }
}