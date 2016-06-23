package sandbox.payroll

import sandbox.validationNotification.ValidationObserver

class BuilderObserver implements ValidationObserver{

    private BuilderStrategy builderStrategy = new BuilderSuccessStrategy()
    private mandatoryObligations = [:]

    public void doOnSuccess(builtObject, aSuccessClosure) {
        builderStrategy.doOnSuccess(builtObject, aSuccessClosure)
    }

    @Override
    void startValidation(String validationName) {
        //don't care
    }

    @Override
    void issueMandatoryObligation(String mandatoryValidationName, String error) {
        mandatoryObligations.put(mandatoryValidationName, error)
        builderStrategy = new BuilderFailureStrategy();
    }

    @Override
    void issueMandatoryObligationComplied(String mandatoryValidationName) {
        mandatoryObligations.remove(mandatoryValidationName)
        if(mandatoryObligations.isEmpty()){ builderStrategy = new BuilderSuccessStrategy() }
    }

    @Override
    void issueError(String error) {
        builderStrategy = new BuilderFailureStrategy();
    }

    @Override
    void finishValidation(String validationName) {
        //don't care
    }

    @Override
    Boolean successful() {
        return builderStrategy.successful()
    }
}
