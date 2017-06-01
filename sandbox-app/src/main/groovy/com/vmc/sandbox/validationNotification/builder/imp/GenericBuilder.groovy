package com.vmc.sandbox.validationNotification.builder.imp

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.ValidationObserver
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.CommonBuilder
/**
 * Provides a generic builder for a given class where with* methods are mapped to constructor arguments in the call
 * order and set* methods are mapped to setters of the built object. It uses Validation Notifiers to figure out if a
 * class was built successfully or not. Others parts interested in the result of validation must only register a new
 * validation observer before starting using this builder. This builder uses any constructor that fits it's
 * configuration, even those private, but when the object is Builder Aware (@see BuilderAwareness) the builder Raises a
 * ForbiddenConstructor if the constructor execution was explicitly marked as invalid
 */
class GenericBuilder implements CommonBuilder, ValidationObserver{

    protected BuilderStrategy builderStrategy = new BuilderSuccessStrategy()
    protected mandatoryObligations = [:]
    protected messagesCall = new HashMap()
    protected constructorArgs = new ArrayList()
    protected Class aClass

    public GenericBuilder(Class aClass) {
        if(aClass == null ) throw new IllegalArgumentException("A class to build must be provided")
        this.aClass = aClass
    }

    protected GenericBuilder(Class aClass, messagesCall, constructorArgs) {
        this.messagesCall = new HashMap(messagesCall)
        this.constructorArgs = new ArrayList(constructorArgs)
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
        validateConstructorUsed(builtEntity)
        messagesCall.each {String name, args -> builtEntity."${name}"(*args) }
        ApplicationValidationNotifier.removeObserver(this)
        return builderStrategy.doWithBuiltEntity(builtEntity, aSuccessClosure, aFailureClosure)
    }

    public void validateConstructorUsed(builtEntity) {
        if (builtEntity instanceof BuilderAwareness && !builtEntity.wasBuiltWithValidConstructor()) {
            throw new UsedForbiddenConstructor("The constructor found for ${aClass.getSimpleName()} with " +
                                               "${constructorArgs.collect { it.getClass().simpleName }} arguments is of " +
                                               "forbidden use.")
        }
    }

    @Override
    void validationStarted(Object subject, Map context, String validationName) {
        //don't care
    }

    @Override
    void mandatoryObligationIssued(Object subject, Map context, String mandatoryValidationName, String error) {
        mandatoryObligations.put(mandatoryValidationName, error)
        builderStrategy = new BuilderFailureStrategy();
    }

    @Override
    void mandatoryObligationComplied(Object subject, Map context, String mandatoryValidationName) {
        mandatoryObligations.remove(mandatoryValidationName)
        if(mandatoryObligations.isEmpty()){ builderStrategy = new BuilderSuccessStrategy() }
    }

    @Override
    void errorIssued(Object subject, Map context, String error) {
        builderStrategy = new BuilderFailureStrategy();
    }

    @Override
    void validationFinished(Object subject, Map context) {
        //don't care
    }

    @Override
    Boolean successful() {
        return builderStrategy.successful()
    }
}
