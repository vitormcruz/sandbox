package sandbox.magritte.testGenerator.junit.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.TestsGeneratorForStringDescription

class JUnitTestGenerationDescriptionsExtension {

    public static Description getTestGenerator(Description aDescription, descriptedClass){
        throw new UnsupportedOperationException("") //TODO message
    }

    public static TestsGeneratorForStringDescription getTestGenerator(StringDescription aDescription, descriptedClass){
        return new TestsGeneratorForStringDescription(descriptedClass)
    }



}
