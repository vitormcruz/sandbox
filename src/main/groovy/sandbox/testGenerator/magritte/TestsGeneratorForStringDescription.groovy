package sandbox.testGenerator.magritte
import sandbox.magritte.Description
import sandbox.magritte.MagnitudeDescription
import sandbox.testGenerator.TestScenario

class TestsGeneratorForStringDescription implements MagnitudeDescription {

    def getTestScenarios(){
        return new TestScenario(testName: "This is a thes for string yet to be implemented", clojure: {assert true})
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
