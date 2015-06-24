package sandbox.validator

import org.junit.runner.Result
import org.junit.runner.notification.RunListener
import org.junit.runner.notification.RunNotifier

trait AbstractValidatorTrait implements GroovyInterceptable{

    def looseValidations = []
    def AbstractValidatorTrait subjectOfValidation
    def methodUnderValidation
    private Map<String, Collection<String>> validationsForMethod = [:]

    def ResultInterface validate(){
        //TODO copied from JUnitCore, probably will change in function of specific listeners or notifiers from different layers (presentation, persistence etc) interested on validation result, but I don't know how yet.
        Result result = new Result();
        RunListener listener = result.createListener();
        getNotifier().addFirstListener(listener);
        try {
            getNotifier().fireTestRunStarted(getValidatorRunner().getDescription());
            getValidatorRunner().run(getNotifier());
            getNotifier().fireTestRunFinished(result);
        } finally {
            getNotifier().removeListener(listener);
        }

        return result as ResultInterface;
    }

    def void validateFailingOnError(){
        def result = validate()
        if(!result.wasSuccessful()) throw new ValidationException(result)
    }

    public abstract ParentValidatorRunner getValidatorRunner()
    public abstract RunNotifier getNotifier()

    def LooseValidationBuilder classifying(String methodUnderValidation) {
        return new LooseValidationBuilder(this, methodUnderValidation)
    }

    def LooseValidationBuilder addValidation(String validationName, Closure validation) {
        looseValidations.add([validationName: validationName, validation: validation])
        return this
    }

    def invokeMethod(String name, Object args) {
        def validationMethodForOperation = metaClass.getMetaMethod("validationFor_$name")
        if(validationMethodForOperation != null){
            validationMethodForOperation.invoke(this, args)
        }

        //TODO I am not sure if it is the best way of doing this. I could use getMetaMethod, but it will not return methods from superclass by the tie I write this
        return metaClass.methods.find {it.name.equals(name)}.invoke(this, args)
    }
}
