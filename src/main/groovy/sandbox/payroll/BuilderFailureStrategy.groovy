package sandbox.payroll

class BuilderFailureStrategy implements BuilderStrategy{

    @Override
    boolean successful() {
        return false
    }

    @Override
    void doOnSuccess(builtObject, aSuccessClosure) {
        //do nothing since building was not successful
    }
}
