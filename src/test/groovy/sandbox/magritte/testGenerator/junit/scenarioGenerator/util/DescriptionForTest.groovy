package sandbox.magritte.testGenerator.junit.scenarioGenerator.util

import sandbox.magritte.description.Description
import sandbox.magritte.testGenerator.SimpleTestScenario


class DescriptionForTest implements Description{
    static def testScenarios = [new SimpleTestScenario(UUID.randomUUID().toString(), {assert true})]

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

    public Collection<SimpleTestScenario> asTestScenariosFor(descriptedClass){
        testScenarios
    }

}
