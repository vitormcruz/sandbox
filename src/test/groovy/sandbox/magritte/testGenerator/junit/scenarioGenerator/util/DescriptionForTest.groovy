package sandbox.magritte.testGenerator.junit.scenarioGenerator.util

import sandbox.magritte.description.Description
import sandbox.magritte.testGenerator.SimpleTestScenario


class DescriptionForTest implements Description{
    static def testScenarios = [new SimpleTestScenario(UUID.randomUUID().toString(), {assert true})]

    public Collection<SimpleTestScenario> asTestScenariosFor(descriptedClass){
        testScenarios
    }

}
