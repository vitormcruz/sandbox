package sandbox.validator
import org.junit.runner.Description
import org.junit.runner.notification.Failure

class LooseValidationBuilder {

    def looseValidations = []
    def AbstractValidatorTrait subjectOfValidation
    def methodUnderValidation

    LooseValidationBuilder(AbstractValidatorTrait subjectOfValidation, String classification) {
        this.subjectOfValidation = subjectOfValidation
        this.methodUnderValidation = classification
    }

    def LooseValidationBuilder addValidation(String validationName, Closure validation) {
        looseValidations.add([validationName: validationName, validation: validation])
        return this
    }

    def void validateFailingOnError(){
        def result = validate()
        if(!result.wasSuccessful()) throw new ValidationException(result)
    }

    def ResultInterface validate(){
        ResultInterface result = subjectOfValidation.validate()
        def listener = result.createListener()
        def description = Description.createSuiteDescription(methodUnderValidation)
        looseValidations.each {
            def looseValidationDescription = Description.createTestDescription(subjectOfValidation.getClass().getSimpleName(), it.validationName)
            listener.testRunStarted(looseValidationDescription)
            try {
                it.validation()
            } catch (Throwable error) {
                listener.testFinished(looseValidationDescription)
                listener.testFailure(new Failure(looseValidationDescription, error))
            }
            listener.testFinished(looseValidationDescription)
        }
        return result
    }

}
