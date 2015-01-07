package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.testGenerator.TestGenerator
import sandbox.magritte.testGenerator.SimpleTestScenario
import sandbox.magritte.testGenerator.description.TestDescription

abstract class TestGeneratorForTestDescription implements TestDescription, TestGenerator {

    Collection<SimpleTestScenario> testScenarios = []

    @Override
    def TestDescription descriptionsFor(Class classUnderTest, Description... descriptions) {
        if(classUnderTest == null){
            throw new IllegalArgumentException("Cannot create test scenarios for a TestDescription that do not specify a target class")
        }

        descriptions.each {
            testScenarios.addAll(getTestsOf(it, classUnderTest))
        }

        return this
    }

    abstract Collection<? extends SimpleTestScenario> getTestsOf(Description description, Class classUnderTest)

    Collection<SimpleTestScenario> getTestScenarios() {
        return testScenarios
    }
}
