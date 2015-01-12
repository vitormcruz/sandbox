package sandbox.magritte.testGenerator.util

import sandbox.magritte.description.Description
import sandbox.magritte.methodGeneration.imp.SimpleGeneratedMethod


class DescriptionForTest implements Description{
    static def testScenarios = [new SimpleGeneratedMethod(UUID.randomUUID().toString(), {assert true})]

    public Collection<SimpleGeneratedMethod> asTestScenariosFor(descriptedClass){
        testScenarios
    }

}
