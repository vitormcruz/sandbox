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
        return teachClassToTestEachScenario(aClass, testScenarios)
    }

    private Collection<TestScenario> getTestScenariosFor(Class aClass) {
        MagritteDescriptionFactory.forObject(aClass.newInstance()).asTestScenariosFor(aClass)
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
