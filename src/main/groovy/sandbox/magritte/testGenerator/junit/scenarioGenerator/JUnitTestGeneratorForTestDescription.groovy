package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription

class JUnitTestGeneratorForTestDescription extends TestGeneratorForTestDescription {

    @Override
    Collection<? extends SimpleGeneratedMethod> getTestsOf(Description aDescription, Class classUnderTest) {
        def testGenerator = aDescription.getMyTestGenerator(classUnderTest)
        aDescription.accept(testGenerator)
        return testGenerator.getGeneratedMethods()
    }

}
