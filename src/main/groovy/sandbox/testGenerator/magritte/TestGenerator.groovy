package sandbox.testGenerator.magritte

import sandbox.testGenerator.TestScenario


interface TestGenerator {

    Collection<TestScenario> getTestScenarios();

}