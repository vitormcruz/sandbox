package sandbox.heavyValidation
import sandbox.validatorJunit.ValidatorTrait
import sandbox.validatorJunit.Validation
/**
 */
class HeavyValidationClass implements ValidatorTrait{

    def Long smallWait = 100
    def Long mediumWait = 800
    def Long longWait = 2000


    @Validation
    def void validate1(){
        sleep(smallWait)
    }

    @Validation
    def void validate2(){
        sleep(smallWait)
    }

    @Validation
    def void validate3(){
        sleep(mediumWait)
    }

    @Validation
    def void validate4(){
        sleep(smallWait)
    }

    @Validation
    def void validate5(){
        sleep(mediumWait)
    }

    @Validation
    def void validate6(){
        sleep(smallWait)
    }

    @Validation
    def void validate7(){
        sleep(smallWait)
    }

    @Validation
    def void validate8(){
        sleep(smallWait)
    }

    @Validation
    def void validate9(){
        sleep(smallWait)
    }

    @Validation
    def void validate10(){
        sleep(longWait)
    }

    @Validation
    def void validate11(){
        sleep(mediumWait)
    }

    @Validation
    def void validate12(){
        sleep(smallWait)
    }

    @Validation
    def void validate13(){
        sleep(mediumWait)
    }

    @Validation
    def void validate14(){
        sleep(smallWait)
    }

    @Validation
    def void validate15(){
        sleep(smallWait)
    }

    @Validation
    def void validate16(){
        sleep(smallWait)
    }

    @Validation
    def void validate17(){
        sleep(mediumWait)
    }

    @Validation
    def void validate18(){
        sleep(smallWait)
    }

    @Validation
    def void validate19(){
        sleep(smallWait)
    }

    @Validation
    def void validate20(){
        sleep(longWait)
    }

}
