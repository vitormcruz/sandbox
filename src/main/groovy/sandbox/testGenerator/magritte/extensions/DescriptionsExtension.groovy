package sandbox.testGenerator.magritte.extensions

import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
import sandbox.magritte.StringDescription
import sandbox.testGenerator.TestScenario
import sandbox.testGenerator.magritte.TestGeneratorForTestDescription
import sandbox.testGenerator.magritte.TestsGeneratorForStringDescription
import sandbox.testGenerator.magritte.model.TestDescription

class DescriptionsExtension {

    public static Collection<TestScenario> asTestScenarios(TestDescription testDescription){
        def testGenerator = new TestGeneratorForTestDescription()
        testDescription.accept(testGenerator)
        return testGenerator.getTestScenarios()
    }

    //TODO this should not be like this
    public static Collection<TestScenario> asTestScenarios(DescriptionContainer descriptionContainer){
        return []
    }

    public static Collection<TestScenario> asTestScenariosFor(Description aDescription, descriptedClass){
        def testGenerator = aDescription.getTestGenerator(descriptedClass)
        aDescription.accept(testGenerator)
        return testGenerator.getTestScenarios()
    }

    public static Description getTestGenerator(Description aDescription, descriptedClass){
        throw new UnsupportedOperationException("") //TODO message
    }

    public static TestGeneratorForTestDescription getTestGenerator(TestDescription aDescription, descriptedClass){
        return new TestGeneratorForTestDescription(descriptedClass)
    }

    public static TestsGeneratorForStringDescription getTestGenerator(StringDescription aDescription, descriptedClass){
        return new TestsGeneratorForStringDescription(descriptedClass)
    }
}
