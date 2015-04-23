package sandbox.magritte.testGenerator

import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.magritte.testGenerator.description.TestDescription

abstract class TestGeneratorForTestDescription implements TestDescription, MethodGenerator {

    Collection<GeneratedMethod> testScenarios
    def protected mandatoryTestGenerator = new MandatoryTestGenerator();

    @Override
    def TestDescription descriptionsFor(Class classUnderTest, Description... descriptions) {
        //TODO use validation framework
        if(classUnderTest == null){
            throw new IllegalArgumentException("Cannot create test scenarios for a TestDescription that do not specify a target class")
        }

        mandatoryTestGenerator.setClassUnderTest(classUnderTest)

        testScenarios = descriptions.collectMany {
            def methodGenerator = it.getMyTestGenerator(classUnderTest)
            methodGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
            it.accept(methodGenerator)
            methodGenerator.getGeneratedMethods()
        }

        testScenarios.addAll(mandatoryTestGenerator.getGeneratedMethods())

        return this
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return testScenarios
    }
}
