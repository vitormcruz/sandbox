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

    public static MethodGenerator asTestGeneratorFor(Description aDescription, descriptedClass){
        return aDescription.accept(aDescription.getMyTestGenerator(descriptedClass))
    }

    public static JUnitTestsGeneratorForStringDescription getMyTestGenerator(StringDescription aDescription, descriptedClass){
        return new JUnitTestsGeneratorForStringDescription(descriptedClass)
    }



}
