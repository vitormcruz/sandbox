package sandbox.payroll.external.interfaceAdapter.webservice.springmvc

import org.springframework.http.ResponseEntity
import sandbox.validationNotification.ValidationObserver

public class RestControllerValidationListener implements ValidationObserver{

    private Map<String, Collection> errorsByValidation
    private currentErrors
    private Boolean successful = true
    def private generateResponseStrategy
    def private issueErrorStrategy

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
        println(error)
        currentErrors.add(error)
    }

    @Override
    void finishValidation(String validationName) {
        currentErrors = errorsByValidation.get(null)
    }

    @Override
    Boolean successful() {
        return successful
    }

    public generateResponse(body){
        return generateResponseStrategy(body)
    }

    def private responseOkStrategy = { body ->
        return ResponseEntity.ok(body)
    }

    def private responseFailStrategy = {
        return ResponseEntity.badRequest().body(errorsByValidation)
    }
}
