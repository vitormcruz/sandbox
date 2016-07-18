package sandbox.payroll

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.magritte.description.TestDescription
import sandbox.magritte.testGenerator.junit.JUnit4TestGeneratorRunner
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.ValidationObserver

import static sandbox.magritte.description.builder.DescriptionFactory.New

@RunWith(JUnit4TestGeneratorRunner)
class EmployeeImpTest {

    @BeforeClass
    def static void setUp(){
        ApplicationValidationNotifier.createCurrentListOfListeners()
    }

    @AfterClass static void tearDown(){
        ApplicationValidationNotifier.destroyCurrentListOfListeners()
    }

    @Test
    public void "Payment method is mandatory"(){
        def validationObserver = new SimpleValidationObserver()
        ApplicationValidationNotifier.addObserver(validationObserver)
        new EmployeeImp.EmployeeBuilder().onSuccessDoWithBuiltEmployee {} //TODO change this
        assert validationObserver.getErrors().contains("payroll.employee.paymentMethod.mandatory")
    }

    @DescriptionModelDefinition
    def myTestDescription(){
        return New(TestDescription).descriptionsFor(getEmployeeClass(),
                                                    New(StringDescription).accessor("name").maxSize(50).beRequired(),
                                                    New(StringDescription).accessor("address").maxSize(200).beRequired(),
                                                    New(StringDescription).accessor("email").maxSize(100).beRequired())
    }

    Class<EmployeeImp> getEmployeeClass() {
        return EmployeeImp
    }

    public static class SimpleValidationObserver implements ValidationObserver{

        def errors = []
        def mandatoryObligation = [:]

        @Override
        void startValidation(String validationName) {

        }

        @Override
        void issueMandatoryObligation(String mandatoryValidationName, String error) {
            mandatoryObligation.put(mandatoryValidationName, error)
        }

        @Override
        void issueMandatoryObligationComplied(String mandatoryValidationName) {
            mandatoryObligation.remove(mandatoryValidationName)
        }

        @Override
        void issueError(String error) {
            errors.add(error)
        }

        @Override
        void finishValidation(String validationName) {

        }

        @Override
        Boolean successful() {
            return errors.isEmpty()
        }

        def getErrors() {
            return errors + mandatoryObligation.collect {it.value}
        }
    }
}
