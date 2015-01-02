package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.junit.Test
import sandbox.magritte.description.Description
import sandbox.magritte.testGenerator.TestScenario
import sandbox.magritte.testGenerator.junit.scenarioGenerator.util.DescriptionForTest

import static groovy.test.GroovyAssert.shouldFail
/**
 * Tests the description of test suits.
 */
class TestGeneratorForTestDescriptionTest {

    @Test
    def void "Create a test scenarios for null class"(){
        def ex = shouldFail(IllegalArgumentException,
                            { new TestGeneratorForTestDescription().descriptionsFor(null, [] as Description[]) })
        assert ex.message == "Cannot create test scenarios for a TestDescription that do not specify a target class"
    }

    @Test
    def void "Create a test scenarios without specify any definition for the class under test"(){
        def testDescription = new TestGeneratorForTestDescription().descriptionsFor(ClassUnderTest, [] as Description[])
        assert testDescription.getTestScenarios().isEmpty() : "No definition test suit description should not generate any test methods."
    }

    @Test
    def void "Create a test description specifying one definition for the class under test"(){
        def testDescription = new TestGeneratorForTestDescription().descriptionsFor(ClassUnderTest, new DescriptionForTest())
        assert DescriptionForTest.testScenarios as Set == testDescription.getTestScenarios() as Set :
                "A test suit with a class definition should generate test methods based on the description used."
    }

    @Test
    def void "Create a test description specifying N definitions for the class under test"(){
        def description = new TestGeneratorForTestDescription().descriptionsFor(ClassUnderTest,
                                                                                new DescriptionForTest(),
                                                                                new DescriptionForTest2())
        def testScenariosEsperados = []
        testScenariosEsperados.addAll(DescriptionForTest2.testScenarios)
        testScenariosEsperados.addAll(DescriptionForTest.testScenarios)

        def testCases = description.getTestScenarios()
        assert testScenariosEsperados as Set == testCases as Set : "A test suit with a class definition should generate test methods based on the descriptions used."
    }

    public static class ClassUnderTest{
    }

    //TODO reduce size of these classes

    public static class DescriptionForTest2 implements Description{
        static def testScenarios = [new TestScenario()]

        @Override
        Description acessor(String acessor) {
            return null
        }

        @Override
        Description beRequired() {
            return null
        }

        @Override
        Description defaultValue(Object defaultValue) {
            return null
        }

        @Override
        Description label(Object label) {
            return null
        }

        public Collection<TestScenario> asTestScenariosFor(descriptedClass){
            testScenarios
        }

    }
}
