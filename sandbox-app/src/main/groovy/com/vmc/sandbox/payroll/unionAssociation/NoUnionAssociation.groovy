package com.vmc.sandbox.payroll.unionAssociation

/**
 * I am used when there is no union membership. I am, therefore, a singleton Null Object for UnionAssociation interface, and my instance should
 * be obtained throught the getInstance static method.
 */
class NoUnionAssociation implements UnionAssociation{

    private static validateInstantiation = {/* The first instantiation from my static context is valid. */}
    private static myself = new NoUnionAssociation()

    NoUnionAssociation(){
        validateInstantiation()
        validateInstantiation = {throw new UnsupportedOperationException("I am a singleton, please get my instance throught the getInstance method.")}
    }

    static getInstance(){
        return myself
    }

    @Override
    Boolean isUnionMember() {
        return false
    }
}
