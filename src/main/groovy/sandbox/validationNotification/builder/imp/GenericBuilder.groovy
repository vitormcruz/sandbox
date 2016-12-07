package sandbox.validationNotification.builder.imp

import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.ValidationObserver
import sandbox.validationNotification.builder.BuilderAwareness
import sandbox.validationNotification.builder.CommonBuilder

/**
 * Provides a generic builder for a given class where with* methods are mapped to constructor arguments in the call
 * order and set* methods are mapped to setters of the built object. It uses Validation Notifiers to figure out if a
 * class was built successfully or not. Others parts interested in the result of validation must only register a new
 * validation observer before starting using this builder. This builder uses any constructor that fits it's
 * configuration, even those private, but when the object is Builder Aware (@see BuilderAwareness) the builder Raises a
 * ForbiddenConstructor if the constructor execution was explicitly marked as invalid
 */
class GenericBuilder implements CommonBuilder, ValidationObserver{

    private BuilderStrategy builderStrategy = new BuilderSuccessStrategy()
    private mandatoryObligations = [:]
    private messagesCall = [:]
    private constructorArgs = new ArrayList()
    private Class aClass

    GenericBuilder(Class aClass) {
        this.aClass = aClass
    }

    def methodMissing(String name, def args) {
        if(name.startsWith("with")){
            constructorArgs.addAll(args)

        }else {
            messagesCall.put(name, args)
        }
        return this
    }

    @Override
    public buildAndDoOnSuccess(aSuccessClosure){
        return buildAndDo(aSuccessClosure, {})
    }

    @Override
    public buildAndDoOnFailure(aFailureClosure){
        return buildAndDo({}, aFailureClosure)
    }

    @Override
    public build(){
        return buildAndDo({}, {return null})
    }

    public buildAndDo(aSuccessClosure, aFailureClosure) {
        ApplicationValidationNotifier.addObserver(this)
        def builtEntity = aClass."newInstance"(*constructorArgs)
        if(builtEntity instanceof BuilderAwareness && !builtEntity.wasBuiltWithValidBuilder()){
            throw new UsedForbiddenConstructor("The constructor found for ${aClass.getSimpleName()} with " +
                                               "${constructorArgs.collect { it.getClass().simpleName }} arguments is of " +
                                               "forbidden use.")
        }
        messagesCall.each {String name, args -> builtEntity."${name}"(*args) }
        ApplicationValidationNotifier.removeObserver(this)
        return builderStrategy.doWithBuiltEntity(builtEntity, aSuccessClosure, aFailureClosure)
    }

    @Override
    void startValidation(String validationName) {
        //don't care
    }

    @Override
    void issueMandatoryObligation(String mandatoryValidationName, String error) {
        mandatoryObligations.put(mandatoryValidationName, error)
        builderStrategy = new BuilderFailureStrategy();
    }

    @Override
    void issueMandatoryObligationComplied(String mandatoryValidationName) {
        mandatoryObligations.remove(mandatoryValidationName)
        if(mandatoryObligations.isEmpty()){ builderStrategy = new BuilderSuccessStrategy() }
    }

    @Override
    void issueError(String error) {
        builderStrategy = new BuilderFailureStrategy();
    }

    @Override
    void finishValidation(String validationName) {
        //don't care
    }

    @Override
    Boolean successful() {
        return builderStrategy.successful()
    }
}
