package sandbox.payroll

class BuilderSuccessStrategy implements BuilderStrategy{

    @Override
    boolean successful() {
        return true
    }

    @Override
    void doOnSuccess(builtObject, aSuccessClosure) {
        aSuccessClosure(builtObject)
    }
}
