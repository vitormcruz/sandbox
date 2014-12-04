package sandbox.testGenerator.magritte

import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
import sandbox.magritte.StringDescription
import sandbox.testGenerator.TestScenario


//TODO this could be a generic extension on the magritte package. Implementations, such as for vaadin and test generator, could only fill classesForDescription apropriadamente.
class DescriptionExtension {
    //TODO this definition sure should not be here
    static classesForDescriptions = [ (StringDescription)    : TestsGeneratorForStringDescription,
                                      (DescriptionContainer) : TestGeneratorForContainerDescriptor
                                    ]

    public static Collection<TestScenario> asTestScenariosFor(Description aDescription, descriptedClass){
        def specificDescription = classesForDescriptions.get(aDescription.getClass())
        if(specificDescription == null) return []
        def testGenerator = specificDescription.newInstance(descriptedClass) //TODO if the class do not exist, provide a better exception than a null pointer messed by groovy
        aDescription.accept(testGenerator)
        return testGenerator.getTestScenarios()
    }
}
