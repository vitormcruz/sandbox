package com.vmc.sandbox.validationNotification.builder

/**
 * Provides functionality that give the objects awareness of it's possible build process and interfere with it
 * (new still can be used).
 */
trait BuilderAwareness {

    private Boolean builtWithValidBuilder = true

    /**
     * Tells if a construction is invalid for the builder. Useful to provide constructor used in reflection magic but
     * that should not be used in the normal program execution.
     */
    public Boolean wasBuiltWithValidBuilder(){
        return builtWithValidBuilder
    }

    public void invalidForBuilder(){
        builtWithValidBuilder = false
    }

}