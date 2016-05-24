package sandbox.heavyValidation
import sandbox.validationNotification.ApplicationValidationNotifier
/**
 */
class HeavyValidationClass {

    private static ApplicationValidationNotifier notifier = ApplicationValidationNotifier.smartNewFor(HeavyValidationClass)

    def Long smallWait = 100
    def Long mediumWait = 800
    def Long longWait = 2000


    def void validate1(){
        notifier.executeNamedValidation("validate1", {sleep(smallWait)})
    }

    def void validate2(){
        notifier.executeNamedValidation("validate2", {sleep(smallWait)})
    }

    def void validate3(){
        notifier.executeNamedValidation("validate3", {sleep(mediumWait)})
    }

    def void validate4(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate5(){
        notifier.executeNamedValidation("validate3", {sleep(mediumWait)})
    }

    def void validate6(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate7(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate8(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate9(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate10(){
        notifier.executeNamedValidation("validate3", {sleep(longWait)})
    }

    def void validate11(){
        notifier.executeNamedValidation("validate3", {sleep(mediumWait)})
    }

    def void validate12(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate13(){
        notifier.executeNamedValidation("validate3", {sleep(mediumWait)})
    }

    def void validate14(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate15(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate16(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate17(){
        notifier.executeNamedValidation("validate3", {sleep(mediumWait)})
    }

    def void validate18(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate19(){
        notifier.executeNamedValidation("validate3", {sleep(smallWait)})
    }

    def void validate20(){
        notifier.executeNamedValidation("validate3", {sleep(longWait)})
    }

}
