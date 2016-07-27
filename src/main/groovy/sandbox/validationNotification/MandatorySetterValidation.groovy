package sandbox.validationNotification

class MandatorySetterValidation {

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private String attrLabel
    private String errorIfNull
    private Closure resetMandatoryObligation = {
        notifier.issueMandatoryObligationComplied(attrLabel)
        resetMandatoryObligation = {}
    }

    MandatorySetterValidation(String attrLabel, String errorIfNull) {
        this.attrLabel = attrLabel
        this.errorIfNull = errorIfNull
    }

    public Boolean canSet(value){
        resetMandatoryObligation()
        if (!value) {
            notifier.issueError(attrLabel, errorIfNull)
            return
        }

        return true
    }
}
