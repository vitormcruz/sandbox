package sandbox.magritte.testGenerator.util

import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription

/**
 * Concrete class of TestGeneratorForTestDescription used to test its abstract implementation.
 */
class TestGeneratorForTestDescriptionPartial extends TestGeneratorForTestDescription{

    @Override
    Collection<? extends SimpleGeneratedMethod> getTestsOf(Description description, Class classUnderTest) {
        return description.asTestScenariosFor(classUnderTest)
    }
}
