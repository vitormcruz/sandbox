package sandbox.magritte.testGenerator.junit.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.methodGenerator.imp.ComposedMethodGenerator
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription
import sandbox.magritte.description.TestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForNumberDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForOperationDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForStringDescription

class JUnitTestGenerationDescriptionsExtension {

    public static Description asTestGenerator(Description aDescription, descriptedClass, mandatoryTestGenerator){
        throw new UnsupportedOperationException("No asTestGenerator method was created for description ${aDescription.getInterfaceBeenRecorded()}")
    }

    public static MethodGenerator asTestGenerator(TestDescription aTestDescription){
        return aTestDescription.playbackAt(new TestGeneratorForTestDescription())
    }

    public static MethodGenerator asTestGenerator(Collection aTestDescription){
        def composedMethodGenerator = new ComposedMethodGenerator()
        aTestDescription.each {composedMethodGenerator.addMethodGenerator(it.asTestGenerator())}
        return composedMethodGenerator
    }

    public static JUnitTestsGeneratorForStringDescription asTestGenerator(StringDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitTestsGeneratorForStringDescription(descriptedClass)
        testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitTestsGeneratorForNumberDescription asTestGenerator(NumberDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitTestsGeneratorForNumberDescription(descriptedClass)
        testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitTestsGeneratorForOperationDescription asTestGenerator(OperationDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitTestsGeneratorForOperationDescription(descriptedClass)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }



}
