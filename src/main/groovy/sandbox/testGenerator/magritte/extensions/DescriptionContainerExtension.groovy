package sandbox.testGenerator.magritte.extensions

import sandbox.magritte.DescriptionContainer
import sandbox.testGenerator.TestScenario

//TODO how to garanteee that a description is used against the correct type os class?
class DescriptionContainerExtension {
    public static Collection<TestScenario> asTestScenarios(DescriptionContainer aDescrition){
        return Collections.emptyList()
    }
}
