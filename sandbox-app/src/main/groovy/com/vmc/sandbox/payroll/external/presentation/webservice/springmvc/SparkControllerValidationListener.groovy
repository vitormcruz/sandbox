package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import com.fasterxml.jackson.databind.ObjectMapper
import com.vmc.sandbox.validationNotification.ValidationObserver
import org.apache.http.HttpStatus
import spark.Response

public class SparkControllerValidationListener implements ValidationObserver{

    private Map<String, Collection> errorsByValidation
    private currentErrors
    private mandatoryObligations = [:]
    private Boolean successful = true
    def private fillResponseStrategy
    def private issueErrorStrategy
    def body

    private ObjectMapper mapper = new ObjectMapper()

    SparkControllerValidationListener() {
        currentErrors = new ArrayList()
        errorsByValidation = [null: currentErrors]
        fillResponseStrategy = responseOkStrategy
        issueErrorStrategy = issueFirstErrorStrategy
    }

    public Map<String, Collection> getErrorsByValidation(){
        return errorsByValidation.findAll {!it.value.isEmpty()}
    }

    @Override
    void validationStarted(String validationName) {
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
        fillResponseStrategy = responseFailStrategy
        successful = false
        issueFirstErrorStrategy = issueErrorOnly
    }

    def private issueErrorOnly = { error ->
        currentErrors.add(error)
    }

    @Override
    void validationFinished() {
        currentErrors = errorsByValidation.get(null)
    }

    @Override
    Boolean successful() {
        return successful && mandatoryObligations.isEmpty()
    }

    public fillResponse(Response response){
        mandatoryObligations.each {it.value()}
        mandatoryObligations.clear()
        fillResponseStrategy(response)
    }

    def private responseOkStrategy = {Response res ->
        res.status(HttpStatus.SC_OK)
        res.body(mapper.writeValueAsString(body))
    }

    def private responseFailStrategy = {Response res ->
        res.status(HttpStatus.SC_BAD_REQUEST)
        res.body(mapper.writeValueAsString(errorsByValidation))
    }
}
