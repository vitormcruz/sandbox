package com.vmc.sandbox.validationNotification.builder

/**
 * Provides functionality that give the objects awareness of it's possible build process and interfere with it by saying some constructor
 * is not valid for the building process. Useful to provide a constructor that can be used in reflection magic but that should not be used in the
 * normal program execution.
 *
 * Use ConstructionValidationFailedException to tell the builder that the construction got one or more errors but that it should ignore the exception
 * so that those errors can be collected by validation observers. When no builder is used, the construction will fail normally, this avoids miss
 * use of constructors that notify errors.
 */
trait BuilderAwareness {

    private Boolean builtWithValidConstructor = true

    /**
     * Tells if a construction is invalid for the builder.
     */
    public Boolean wasBuiltWithValidConstructor(){
        return builtWithValidConstructor
    }

    public void invalidForBuilder(){
        builtWithValidConstructor = false
    }
}