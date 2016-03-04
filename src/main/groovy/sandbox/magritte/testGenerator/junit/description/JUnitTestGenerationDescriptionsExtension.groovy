package sandbox.magritte.testGenerator.junit.description

import sandbox.magritte.description.*
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.methodGenerator.imp.ComposedMethodGenerator
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForNumberDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitOperationDescriptionTestGenerator
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitStringDescriptionTestGenerator

class JUnitTestGenerationDescriptionsExtension {

    public static MethodGenerator asTestGenerator(Description aDescription, descriptedClass, mandatoryTestGenerator){
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

    public static JUnitStringDescriptionTestGenerator asTestGenerator(StringDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitStringDescriptionTestGenerator(descriptedClass)
        testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitStringDescriptionTestGenerator asTestGenerator(StringDescription aDescription, descriptedClass, mandatoryTestGenerator, validationMethod){
        def testGenerator = new JUnitStringDescriptionTestGenerator(descriptedClass)
        testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
        testGenerator.setValidationMethod(validationMethod)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitTestsGeneratorForNumberDescription asTestGenerator(NumberDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitTestsGeneratorForNumberDescription(descriptedClass)
        testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitOperationDescriptionTestGenerator asTestGenerator(NewOperationDescription aDescription, descriptedClass, mandatoryTestGenerator){
        def testGenerator = new JUnitOperationDescriptionTestGenerator(descriptedClass)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }



}
