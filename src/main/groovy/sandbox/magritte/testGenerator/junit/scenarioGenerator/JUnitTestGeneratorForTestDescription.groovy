package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGeneration.generator.imp.SimpleGeneratedMethod
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription

class JUnitTestGeneratorForTestDescription extends TestGeneratorForTestDescription {

    @Override
    Collection<? extends SimpleGeneratedMethod> getTestsOf(Description aDescription, Class classUnderTest) {
        def testGenerator = aDescription.getTestGenerator(classUnderTest)
        aDescription.accept(testGenerator)
        return testGenerator.getGeneratedMethods()
    }

}
