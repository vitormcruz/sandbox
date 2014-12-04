package sandbox.testGenerator.magritte
import sandbox.magritte.Description
import sandbox.magritte.MagnitudeDescription
import sandbox.testGenerator.TestScenario

class TestsGeneratorForStringDescription implements MagnitudeDescription {

    private Class descriptedClass

    TestsGeneratorForStringDescription(Class descriptedClass) {
        this.descriptedClass = descriptedClass
    }

    def getTestScenarios(){
        //TODO This is a test for string yet to be implemented
        return [new TestScenario("This is a test for string yet to be implemented", {assert true})]
    }

    @Override
    Description maxSize(Integer maxSize) {
        return null
    }

    @Override
    Description acessor(String acessor) {
        return null
    }

    @Override
    Description beRequired() {
        return null
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return null
    }

    @Override
    Description label(Object label) {
        return null
    }
}
