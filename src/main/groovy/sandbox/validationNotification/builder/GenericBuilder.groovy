package sandbox.validationNotification.builder

import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.CommonBuilder

/**
 * Provides a generic builder for a given class with basic with* methods mapped to setters of the built object. It also
 * uses Validation Notifiers to figure out if a class was built successfully or not. Others parts interested in the
 * result of validation must only register a new validation observer before starting using this builder.
 *
 * Example:
 *
 *
 *
 */
class GenericBuilder implements CommonBuilder{

    protected builderObserver = new BuilderObserver()
    protected builtEntity

    GenericBuilder(){
    }

    GenericBuilder(aClass) {
        ApplicationValidationNotifier.addObserver(this.builderObserver)
        builtEntity = aClass.newInstance()
        builderObserver.setBuiltEntity(builtEntity)
    }

    def methodMissing(String name, def args) {
        def name2 = name.startsWith("with") ? name.replaceFirst("with", "set") : name
        builtEntity."${name2}"(*args)
        return this
    }

    @Override
    public buildAndDoOnSuccess(aSuccessClosure){
        return builderObserver.buildAndDo(aSuccessClosure, {})
    }

    @Override
    public buildAndDoOnFailure(aFailureClosure){
        return builderObserver.buildAndDo({}, aFailureClosure)
    }

    @Override
    public buildAndDo(aSuccessClosure, aFailureClosure){
        return builderObserver.buildAndDo(aSuccessClosure, aFailureClosure)
    }

    @Override
    public buildEntity(){
        return builderObserver.buildAndDo({}, {return null})
    }

    def getDelegate(){
        def self = this
        def delegateProxy = [:]
        builtEntity.getClass().getDeclaredMethods().each() { method ->
            delegateProxy."$method.name" = { args ->
                this."${method.name}"(args)
                return self;
            }
        }

        return delegateProxy.asType(builtEntity.getClass())
    }
}
