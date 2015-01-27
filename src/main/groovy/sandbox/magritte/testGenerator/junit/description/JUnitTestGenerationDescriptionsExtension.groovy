package sandbox.magritte.testGenerator.junit.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForStringDescription

class JUnitTestGenerationDescriptionsExtension {

    public static MethodGenerator getMyTestGenerator(Description aDescription, descriptedClass){
        def validationGenerator = aDescription.getMyTestGenerator()
        return aDescription.accept(validationGenerator)
    }

    public static JUnitTestsGeneratorForStringDescription getMyTestGenerator(StringDescription aDescription, descriptedClass){
        return new JUnitTestsGeneratorForStringDescription(descriptedClass)
    }



}
