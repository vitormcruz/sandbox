package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import org.springframework.http.ResponseEntity
import com.vmc.sandbox.validationNotification.ValidationObserver

public class WebServiceControllerValidationListener implements ValidationObserver{

    private Map<String, Collection> errorsByValidation
    private currentErrors
    private mandatoryObligations = [:]
    private Boolean successful = true
    def private generateResponseStrategy
    def private issueErrorStrategy
    def body

    WebServiceControllerValidationListener() {
        currentErrors = new ArrayList()
        errorsByValidation = [null: currentErrors]
        generateResponseStrategy = responseOkStrategy
        issueErrorStrategy = issueFirstErrorStrategy
    }

    public Map<String, Collection> getErrorsByValidation(){
        return errorsByValidation.findAll {!it.value.isEmpty()}
    }

    @Override
    void validationStarted(Object subject, Map context, String validationName) {
        currentErrors = new ArrayList()
        errorsByValidation.put(validationName, currentErrors)
    }

    @Override
    void mandatoryObligationIssued(Object subject, Map context, String mandatoryValidationName, String error) {
        mandatoryObligations.put(mandatoryValidationName, {errorIssued(subject, context, error)})
    }

    @Override
    void mandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName) {
        mandatoryObligations.remove(mandatoryValidationName)
    }

    @Override
    void errorIssued(Object subject, Map context, String error) {
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
    void validationFinished(Object subject, Map context) {
        currentErrors = errorsByValidation.get(null)
    }

    @Override
    Boolean successful() {
        return successful && mandatoryObligations.isEmpty()
    }

    public generateResponse(){
        mandatoryObligations.each {it.value()}
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
