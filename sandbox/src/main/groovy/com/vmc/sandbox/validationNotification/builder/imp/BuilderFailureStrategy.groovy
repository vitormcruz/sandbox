package com.vmc.sandbox.validationNotification.builder.imp

class BuilderFailureStrategy implements BuilderStrategy{

    @Override
    Boolean successful() {
        return false
    }

    @Override
    public doWithBuiltEntity(builtObject, Object aSuccessClosure, Object aFailureClosure) {
        aFailureClosure()
        return null;
    }
}
