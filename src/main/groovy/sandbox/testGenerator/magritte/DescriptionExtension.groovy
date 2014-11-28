package sandbox.testGenerator.magritte

import sandbox.magritte.Description
import sandbox.testGenerator.TestScenario

class DescriptionExtension {
    static classesForDescriptions = [:]

    public static Collection<TestScenario> asTestScenariosFor(Description aDescription, descriptedClass){
        def testGenerator = classesForDescriptions.get(aDescription.getClass()).newInstance(descriptedClass) //TODO if the class do not exist, provide a better exception than a null pointer messed by groovy
        aDescription.accept(testGenerator)
        return testGenerator.getTestScenarios()
    }
}
