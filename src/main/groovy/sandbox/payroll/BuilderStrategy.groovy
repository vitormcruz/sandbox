package sandbox.payroll

interface BuilderStrategy {
    boolean successful()
    void doOnSuccess(builtObject, aSuccessClosure)
}
