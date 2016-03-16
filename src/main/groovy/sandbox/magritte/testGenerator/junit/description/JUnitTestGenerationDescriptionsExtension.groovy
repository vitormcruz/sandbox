package sandbox.magritte.testGenerator.junit.description
import sandbox.magritte.description.Description
import sandbox.magritte.description.NewOperationDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.description.TestDescription
import sandbox.magritte.methodGenerator.imp.ComposedMethodGenerator
import sandbox.magritte.testGenerator.TestContext
import sandbox.magritte.testGenerator.TestGeneratorForTestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitOperationDescriptionTestGenerator
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitStringDescriptionTestGenerator
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JunitObjectDescriptionTestGenerator

class JUnitTestGenerationDescriptionsExtension {

    public static JunitObjectDescriptionTestGenerator asTestGenerator(Description aDescription, TestContext testContext){
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

    public static JUnitStringDescriptionTestGenerator asTestGenerator(StringDescription aDescription, TestContext testContext){
        def testGenerator = new JUnitStringDescriptionTestGenerator(testContext)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }

    public static JUnitOperationDescriptionTestGenerator asTestGenerator(NewOperationDescription aDescription, TestContext testContext){
        def testGenerator = new JUnitOperationDescriptionTestGenerator(testContext)
        aDescription.playbackAt(testGenerator)
        return testGenerator
    }



}
