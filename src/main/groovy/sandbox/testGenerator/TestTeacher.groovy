package sandbox.testGenerator

import sandbox.magritte.MagritteDescriptionFactory
/**
 * <pre>
 *
 * Teach a test class how to test according to its test description.
 *
 * Example:
 *
 * </pre>
 */
class TestTeacher {
    def teach(Class aClass) {
        Collection<TestScenario> testScenarios = getTestScenariosFor(aClass)
        teachClassToTestEachScenario(aClass, testScenarios)
        aClass.metaClass.static.getMagritteTests = { testScenarios.collect {it.testName} }
    }

    private Collection<TestScenario> getTestScenariosFor(Class aClass) {
        MagritteDescriptionFactory.forObject(aClass.newInstance()).asTestScenariosFor(aClass)
    }

    private Iterable<TestScenario> teachClassToTestEachScenario(aClass, Collection<TestScenario> testScenarios) {
        testScenarios.each { aClass.metaClass."${it.testName}" << it.getClojure() }
    }
}
