package sandbox.validationNotification.builder

interface BuilderStrategy {
    Boolean successful()
    def doWithBuiltEntity(builtObject, aSuccessClosure, aFailureClosure)
}
