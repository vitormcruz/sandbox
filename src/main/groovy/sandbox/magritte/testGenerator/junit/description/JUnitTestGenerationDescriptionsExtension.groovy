package sandbox.magritte.testGenerator.junit.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription
import sandbox.magritte.testGenerator.description.TestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForStringDescription

class JUnitTestGenerationDescriptionsExtension {

    public static MethodGenerator asTestGenerator(TestDescription aTestDescription){
        return aTestDescription.playbackAt(new TestGeneratorForTestDescription())
    }

    public static JUnitTestsGeneratorForStringDescription asTestGenerator(Description aDescription, descriptedClass, mandatoryTestGenerator){
        throw new UnsupportedOperationException("No asTestGenerator method was created for description ${aDescription.getClass().getName()}")
    }

    public static JUnitTestsGeneratorForStringDescription asTestGenerator(StringDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitTestsGeneratorForStringDescription(descriptedClass)
        testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }



}
