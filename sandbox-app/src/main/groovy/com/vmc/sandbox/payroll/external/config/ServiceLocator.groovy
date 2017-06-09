package com.vmc.sandbox.payroll.external.config

class ServiceLocator {

    private static validateInstantiation = {/* The first instantiation from my static context is valid. */}
    private static myself = new ServiceLocator()

    ServiceLocator(){
        validateInstantiation()
        validateInstantiation = {throw new UnsupportedOperationException("I am a singleton, please get my instance throught the getInstance method.")}
    }

    static getInstance(){
        return myself
    }
}
