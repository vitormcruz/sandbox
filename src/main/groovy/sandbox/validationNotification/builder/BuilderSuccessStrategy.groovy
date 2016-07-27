package sandbox.validationNotification.builder

class BuilderSuccessStrategy implements BuilderStrategy{

    @Override
    Boolean successful() {
        return true
    }

    @Override
    public doWithBuiltEntity(Object builtObject, Object aSuccessClosure, Object aFailureClosure) {
        aSuccessClosure(builtObject)
        return builtObject
    }
}
