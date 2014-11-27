package sandbox.testGenerator.magritte

import sandbox.magritte.DescriptionContainer
import sandbox.testGenerator.TestScenario

//TODO how to garanteee that a description is used against the correct type os class?
class DescriptionContainerExtension {
    public static Collection<TestScenario> asTestScenarios(DescriptionContainer aDescrition){

        //TODO this should be visitable
        if(aDescrition.getForClass() == null){
            throw new IllegalArgumentException("Cannot create test scenarios for a DescriptionContainer that do not specify a target class")
        }

        return []
    }
}
