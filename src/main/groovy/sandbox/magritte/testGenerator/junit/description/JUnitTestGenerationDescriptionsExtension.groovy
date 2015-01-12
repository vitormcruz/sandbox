package sandbox.magritte.testGenerator.junit.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.JUnitTestsGeneratorForStringDescription

class JUnitTestGenerationDescriptionsExtension {

    public static Description getTestGenerator(Description aDescription, descriptedClass){
        throw new UnsupportedOperationException("") //TODO message
    }

    public static JUnitTestsGeneratorForStringDescription getTestGenerator(StringDescription aDescription, descriptedClass){
        return new JUnitTestsGeneratorForStringDescription(descriptedClass)
    }



}
