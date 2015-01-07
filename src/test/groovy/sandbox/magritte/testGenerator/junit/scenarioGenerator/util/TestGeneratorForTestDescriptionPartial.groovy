package sandbox.magritte.testGenerator.junit.scenarioGenerator.util

import sandbox.magritte.description.Description
import sandbox.magritte.testGenerator.SimpleTestScenario
import sandbox.magritte.testGenerator.junit.scenarioGenerator.TestGeneratorForTestDescription

/**
 * Concrete class of TestGeneratorForTestDescription used to test its abstract implementation.
 */
class TestGeneratorForTestDescriptionPartial extends TestGeneratorForTestDescription{

    @Override
    Collection<? extends SimpleTestScenario> getTestsOf(Description description, Class classUnderTest) {
        return description.asTestScenariosFor(classUnderTest)
    }
}
