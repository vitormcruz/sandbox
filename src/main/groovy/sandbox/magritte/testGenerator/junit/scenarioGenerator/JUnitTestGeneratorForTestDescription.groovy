package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.testGenerator.SimpleTestScenario

class JUnitTestGeneratorForTestDescription extends TestGeneratorForTestDescription {
    @Override
    Collection<? extends SimpleTestScenario> getTestsOf(Description aDescription, Class classUnderTest) {
        def testGenerator = aDescription.getTestGenerator(classUnderTest)
        aDescription.accept(testGenerator)
        return testGenerator.getTestScenarios()
    }

}
