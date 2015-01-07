package sandbox.magritte.testGenerator
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.testGenerator.junit.scenarioGenerator.TestGeneratorForTestDescription

/**
 * <pre>
 *
 * Teach a test class how to test according to its test description.
 *
 * Runers specifc implementations should provide a way to tell their frameworks that the methods created are
 * tests.
 *
 * Example:
 *
 * </pre>
 */
class TestTeacher {

    private Class<? extends TestGeneratorForTestDescription> testDescriptionGeneratorClass

    TestTeacher(Class<? extends TestGeneratorForTestDescription> testDescriptionGeneratorClass) {
        validateTestDescriptorgeneratorClass(testDescriptionGeneratorClass)
        this.testDescriptionGeneratorClass = testDescriptionGeneratorClass
    }

    //TODO framework validation
    private void validateTestDescriptorgeneratorClass(Class<? extends TestGeneratorForTestDescription> testDescriptionGeneratorClass) {
        if (testDescriptionGeneratorClass == null) {
            throw new IllegalArgumentException("You should provide a TestGenerator implementation class")
        }
    }

    def teach(Class aClass) {
        Collection<SimpleTestScenario> testScenarios = getTestScenariosFor(aClass)
        return teachClassToTestEachScenario(aClass, testScenarios)
    }

    private Collection<SimpleTestScenario> getTestScenariosFor(Class aClass) {
        def descriptionModel = MagritteDescriptionModelBuilder.forObject(aClass.newInstance())
        def testGenerator = testDescriptionGeneratorClass.newInstance()
        descriptionModel.accept(testGenerator)
        return testGenerator.getTestScenarios()
    }

    private Collection<MetaMethod> teachClassToTestEachScenario(aClass, Collection<SimpleTestScenario> testScenarios) {
        Collection<MetaMethod> methodsCreatedForTest = []
        testScenarios.each {
            aClass.metaClass."${it.testName}" << it.getClojure()
            methodsCreatedForTest.add(aClass.metaClass.getMetaMethod(it.testName))
        }

        return methodsCreatedForTest
    }
}
