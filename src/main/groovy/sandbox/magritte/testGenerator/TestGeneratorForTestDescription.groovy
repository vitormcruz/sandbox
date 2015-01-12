package sandbox.magritte.testGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGeneration.generator.GeneratedMethod
import sandbox.magritte.methodGeneration.generator.imp.SimpleGeneratedMethod
import sandbox.magritte.methodGeneration.description.MethodGenerator
import sandbox.magritte.testGenerator.description.TestDescription

abstract class TestGeneratorForTestDescription implements TestDescription, MethodGenerator {

    Collection<SimpleGeneratedMethod> testScenarios = []

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

    abstract Collection<? extends SimpleGeneratedMethod> getTestsOf(Description description, Class classUnderTest)

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return testScenarios
    }
}
