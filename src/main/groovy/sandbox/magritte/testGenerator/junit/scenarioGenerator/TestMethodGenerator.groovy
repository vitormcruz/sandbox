package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.testGenerator.TestContext

interface TestMethodGenerator {

    Collection<GeneratedMethod> generateMethods(TestContext testContext);

}