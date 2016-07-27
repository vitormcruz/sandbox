package sandbox.validationNotification.builder

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
