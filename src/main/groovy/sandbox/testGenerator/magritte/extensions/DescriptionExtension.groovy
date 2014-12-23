package sandbox.testGenerator.magritte.extensions

import sandbox.magritte.Description
import sandbox.magritte.StringDescription
import sandbox.testGenerator.TestScenario
import sandbox.testGenerator.magritte.TestGeneratorForContainerDescriptor
import sandbox.testGenerator.magritte.TestsGeneratorForStringDescription
import sandbox.testGenerator.magritte.model.TestDescriptionContainer

class DescriptionExtension {
    //TODO this definition sure should not be here
    static classesForDescriptions = [ (StringDescription)    : TestsGeneratorForStringDescription,
                                      (TestDescriptionContainer) : TestGeneratorForContainerDescriptor
                                    ]

    public static Collection<TestScenario> asTestScenariosFor(Description aDescription, descriptedClass){
        def specificDescription = classesForDescriptions.get(aDescription.getClass())
        if(specificDescription == null) return []
        def testGenerator = specificDescription.newInstance(descriptedClass) //TODO if the class do not exist, provide a better exception than a null pointer messed by groovy
        aDescription.accept(testGenerator)
        return testGenerator.getTestScenarios()
    }
}
