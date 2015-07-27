package sandbox.validator

import org.junit.runner.Result
import org.junit.runner.notification.RunListener
import org.junit.runner.notification.RunNotifier
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod

trait AbstractValidatorTrait implements GroovyInterceptable{

    //TODO Avoid this list or see if it is necessary to use thread local.
    def validations = new ArrayList()

    def ResultInterface validate(){
        getGeneratedMethods().each {
            it.teachMyselfTo(this)
        }
        return runValidations()
    }

    /**
     * Once all validations are set, this method may be called to run them.
     * @return
     */
    def ResultInterface runValidations() {
        Result result = new Result();
        RunListener listener = result.createListener();
        getNotifier().addFirstListener(listener);
        try {
            getNotifier().fireTestRunStarted(getValidatorRunner().getDescription());
            getValidatorRunner().run(getNotifier());
            getNotifier().fireTestRunFinished(result);
        } finally {
            getNotifier().removeListener(listener);
            validations.clear()
        }

        return result as ResultInterface;
    }

    def void validateFailingOnError(){
        def result = validate()
        if(!result.wasSuccessful()) throw new ValidationException(result)
    }

    def ResultInterface validateFor(String operation, Object... params){
        getGeneratedMethods().each {
            it.teachMyselfToInContext(this, operation, params)
        }

        return runValidations()
    }

    private Collection<GeneratedMethod> getGeneratedMethods() {
        return MagritteDescriptionModelBuilder.forObject(this).asMethodGenerator().getGeneratedMethods()
    }

    def void validateFailingOnErrorFor(String operation, Object... params){
        def result = validateFor(operation, params)
        if(!result.wasSuccessful()) throw new ValidationException(result)
    }

    public abstract ParentValidatorRunner getValidatorRunner()
    public abstract RunNotifier getNotifier()

    def AbstractValidatorTrait addValidation(String validationName, Closure validation) {
        return addValidation(validationName, validation, "")
    }

    def AbstractValidatorTrait addValidation(String validationName, Closure validation, String classification) {
        validations.add([validationName: validationName, validation: validation, classification:classification])
        return this
    }

    def executeClosureValidation(Closure validation, Object[] params){
        validation(params)
    }
}
