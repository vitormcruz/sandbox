package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.magritte.testGenerator.TestScenario


interface TestGenerator {

    Collection<TestScenario> getTestScenarios();

}