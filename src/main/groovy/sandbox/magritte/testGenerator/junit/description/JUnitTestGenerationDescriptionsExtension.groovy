package sandbox.magritte.testGenerator.junit.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.testGenerator.description.TestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestGeneratorForTestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForStringDescription

class JUnitTestGenerationDescriptionsExtension {

    public static MethodGenerator asTestGenerator(TestDescription aTestDescription){
        return aTestDescription.accept(new JUnitTestGeneratorForTestDescription())
    }

    public static JUnitTestsGeneratorForStringDescription asTestGenerator(StringDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitTestsGeneratorForStringDescription(descriptedClass)
        testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
        aDescription.accept(testGenerator)
        return testGenerator
    }



}
