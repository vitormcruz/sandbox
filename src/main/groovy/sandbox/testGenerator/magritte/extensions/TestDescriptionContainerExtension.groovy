package sandbox.testGenerator.magritte.extensions

import sandbox.testGenerator.TestScenario
import sandbox.testGenerator.magritte.TestGeneratorDescriptionContainer
import sandbox.testGenerator.magritte.model.TestDescriptionContainer


class TestDescriptionContainerExtension {

    public static Collection<TestScenario> asTestScenarios(TestDescriptionContainer testDescriptionContainer){

        //TODO this should be visitable
        if(testDescriptionContainer.getForClass() == null){
            throw new IllegalArgumentException("Cannot create test scenarios for a DescriptionContainer that do not specify a target class")
        }

        def testGenerator = new TestGeneratorDescriptionContainer(testDescriptionContainer.getForClass())
        testDescriptionContainer.accept(testGenerator)

        return testGenerator.getTestScenarios()
    }
}
