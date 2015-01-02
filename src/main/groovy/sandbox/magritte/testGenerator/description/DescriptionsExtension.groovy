package sandbox.magritte.testGenerator.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.StringDescription
import sandbox.magritte.testGenerator.TestScenario
import sandbox.magritte.testGenerator.junit.scenarioGenerator.TestGeneratorForTestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.TestsGeneratorForStringDescription

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
