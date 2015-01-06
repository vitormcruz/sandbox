package sandbox.magritte.testGenerator
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
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
    def teach(Class aClass) {
        Collection<TestScenario> testScenarios = getTestScenariosFor(aClass)
        return teachClassToTestEachScenario(aClass, testScenarios)
    }

    private Collection<TestScenario> getTestScenariosFor(Class aClass) {
        MagritteDescriptionModelBuilder.forObject(aClass.newInstance()).asTestScenarios()
    }

    private Collection<MetaMethod> teachClassToTestEachScenario(aClass, Collection<TestScenario> testScenarios) {
        Collection<MetaMethod> methodsCreatedForTest = []
        testScenarios.each {
            aClass.metaClass."${it.testName}" << it.getClojure()
            methodsCreatedForTest.add(aClass.metaClass.getMetaMethod(it.testName))
        }

        return methodsCreatedForTest
    }
}
