package sandbox.magritte.testGenerator.junit.description
import sandbox.magritte.description.Description
import sandbox.magritte.description.NewOperationDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.description.TestDescription
import sandbox.magritte.methodGenerator.imp.ComposedMethodGenerator
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitOperationDescriptionTestGenerator
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitStringDescriptionTestGenerator
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JunitObjectDescriptionTestGenerator

class JUnitTestGenerationDescriptionsExtension {

    public static JunitObjectDescriptionTestGenerator asTestGenerator(Description aDescription, descriptedClass){
        throw new UnsupportedOperationException("No asTestGenerator method was created for description ${aDescription.getInterfaceBeenRecorded()}")
    }

    public static TestGeneratorForTestDescription asTestGenerator(TestDescription aTestDescription){
        return aTestDescription.playbackAt(new TestGeneratorForTestDescription())
    }

    public static ComposedMethodGenerator asTestGenerator(Collection aTestDescription){
        def composedMethodGenerator = new ComposedMethodGenerator()
        aTestDescription.each {composedMethodGenerator.addMethodGenerator(it.asTestGenerator())}
        return composedMethodGenerator
    }

    public static JUnitStringDescriptionTestGenerator asTestGenerator(StringDescription aDescription, describedClass){
        def testGenerator = new JUnitStringDescriptionTestGenerator(describedClass)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitStringDescriptionTestGenerator asTestGenerator(StringDescription aDescription, describedClass, validationMethod){
        def testGenerator = new JUnitStringDescriptionTestGenerator(describedClass)
        testGenerator.setValidationMethod(validationMethod)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitOperationDescriptionTestGenerator asTestGenerator(NewOperationDescription aDescription, describedClass){
        def testGenerator = new JUnitOperationDescriptionTestGenerator(describedClass)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }



}
