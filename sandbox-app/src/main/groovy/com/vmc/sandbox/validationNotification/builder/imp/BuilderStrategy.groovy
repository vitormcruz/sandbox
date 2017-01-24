package com.vmc.sandbox.validationNotification.builder.imp

interface BuilderStrategy {
    Boolean successful()
    def doWithBuiltEntity(builtObject, aSuccessClosure, aFailureClosure)
}
