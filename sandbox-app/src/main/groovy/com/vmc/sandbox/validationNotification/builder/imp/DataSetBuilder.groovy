package com.vmc.sandbox.validationNotification.builder.imp
/**
 * Act as a builder, but receives a closure to insert object on the database at the end
 */
class DataSetBuilder extends GenericBuilder {

    protected Closure insertCommand

    public DataSetBuilder(Class aClass, Closure insertCommand) {
        super(aClass)
        if(insertCommand == null ) throw new IllegalArgumentException("An insertCommand closure must be provided")
        this.insertCommand = insertCommand
    }

    protected DataSetBuilder(Class aClass, messagesCall, constructorArgs, Closure insertCommand) {
        super(aClass, messagesCall, constructorArgs)
        this.insertCommand = insertCommand
    }

    def methodMissing(String name, Object args) {
        return cloneMe().clonedMethodMissing(name, args)
    }

    def cloneMe(){
        return new DataSetBuilder(aClass, messagesCall, constructorArgs, insertCommand)
    }

    def clonedMethodMissing(String name, Object args){
        return super.methodMissing(name, args)
    }

    @Override
    def buildAndDo(Object aSuccessClosure, Object aFailureClosure) {
        def builtObject = super.buildAndDo(aSuccessClosure, aFailureClosure)
        builderStrategy.doWithBuiltEntity(builtObject, insertCommand,
                                                            {//TODO print validation result
                                                                         })
        return builtObject
    }
}