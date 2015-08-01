package sandbox.validator

import org.junit.runner.Result
import org.junit.runner.notification.RunListener
import org.junit.runner.notification.RunNotifier
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.validator.imp.ValidationException
import sandbox.validator.imp.ValidatorRunner

trait ValidatorTrait implements GroovyInterceptable{

    protected ThreadLocal<Collection> validations = {
        def validations = new ThreadLocal<Collection>()
        validations.set(new ArrayList())
        return validations
    }()

    def protected runNotifier = new RunNotifier()

    public RunNotifier getNotifier(){
        return runNotifier
    }

    public Collection getValidations(){
        return validations.get()
    }

    def void validateFailingOnError(){
        def result = validate()
        if(!result.wasSuccessful()) throw new ValidationException(result)
    }

    def ResultInterface validate(){
        getGeneratedMethods().each { it.teachMyselfTo(this) }
        return runValidations()
    }

    def void validateFailingOnErrorFor(String operation, Object... params){
        def result = validateFor(operation, params)
        if(!result.wasSuccessful()) throw new ValidationException(result)
    }

    def ResultInterface validateFor(String operation, Object... params){
        getGeneratedMethods().each { it.teachMyselfToInContext(this, operation, params) }
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
            def validatorRunner = newValidatorRunner()
            getNotifier().fireTestRunStarted(validatorRunner.getDescription());
            validatorRunner.run(getNotifier());
            getNotifier().fireTestRunFinished(result);
        } finally {
            getNotifier().removeListener(listener);
            validations.get().clear()
        }

        return result as ResultInterface;
    }

    private Collection<GeneratedMethod> getGeneratedMethods() {
        return MagritteDescriptionModelBuilder.forObject(this).asMethodGenerator().getGeneratedMethods()
    }

    public ParentValidatorRunner newValidatorRunner(){
        return new ValidatorRunner(this)
    }

    def ValidatorTrait withValidation(String validationName, Closure validation) {
        return withValidation(validationName, validation, "")
    }

    def ValidatorTrait withValidation(String validationName, Closure validation, String classification) {
        validations.get().add([validationName: validationName, validation: validation, classification:classification])
        return this
    }
}
