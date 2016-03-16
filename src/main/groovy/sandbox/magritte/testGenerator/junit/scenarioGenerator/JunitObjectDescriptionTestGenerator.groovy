package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.testGenerator.TestContext

abstract class JunitObjectDescriptionTestGenerator implements ObjectDescription, MethodGenerator {

    def protected String label
    protected TestContext testContext
    protected generators = []

    TestContext getTestContext() {
        return testContext
    }

    ObjectDescription accessor(String accessor) {
        if(testContext.getTestTargetName() == null){
            testContext = testContext.withAliasForTestTarget(accessor)
        }
        return this
    }

    @Override
    ObjectDescription label(String label) {
        testContext = testContext.withAliasForTestTarget(label)
        return this
    }

    @Override
    ObjectDescription beRequired() {
        testContext.getMandatoryTestGenerator().requiredAccessor(testContext.get)
        return this
    }

    @Override
    public Collection<GeneratedMethod> getGeneratedMethods() {
        return generators.collectMany {it.generateMethods(testContext)}
    }

}
