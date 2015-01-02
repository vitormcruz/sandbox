package sandbox.magritte.testGenerator.junit.scenarioGenerator.util

import sandbox.magritte.description.Description
import sandbox.magritte.testGenerator.TestScenario


class DescriptionForTest implements Description{
    static def testScenarios = [new TestScenario(UUID.randomUUID().toString(), {assert true})]

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

    public Collection<TestScenario> asTestScenariosFor(descriptedClass){
        testScenarios
    }

}
