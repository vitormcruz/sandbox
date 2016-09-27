package sandbox.payroll

/**
 * Builder with common and useful protocols to deal with success and failure scenarios
 */
interface CommonBuilder {

    /**
     * Build the object and execute aSuccessClosure upon success. Return the built object or null in case of failure.
     */
    public buildAndDoOnSuccess(aSuccessClosure)

    /**
     * Build the object and execute aSuccessClosure upon success or aFailureClosure otherwise. Return the built object
     * or null in case of failure.
     */
    public buildAndDo(aSuccessClosure, aFailureClosure)

    /**
     * Build the object and execute aFailureClosure upon failure. Return the built object or null in case of failure.
     */
    public buildAndDoOnFailure(aFailureClosure)

    /**
     * Return the successfully built entity or null otherwise
     */
    public buildEntity()
}