package sandbox.testGenerator

import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
import sandbox.magritte.StringDescription
import org.junit.Ignore
import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail
/**
 * Tests the description of test suits.
 */
class GenerateTestFromDescriptionTest {

    @Test
    def void "Create a test scenarios for null class"(){
        def description = new DescriptionContainer(null, [] as Description[])
        def ex = shouldFail(IllegalArgumentException, { description.asTestScenarios() })
        assert ex.message == "Cannot create test scenarios for a DescriptionContainer that do not specify a target class"
    }

    @Test
    def void "Create a test scenarios without specify any definition for the class under test"(){
        def description = new DescriptionContainer(ClassUnderTest, [] as Description[])
        assert description.asTestScenarios().isEmpty() : "No definition test suit description should not generate any test methods"
    }

    @Ignore("Thinking how to implement the TestScenario class")
    @Test
    def void "Create a test description specifying one definition for the class under test"(){
        def testSuitDescription = TestSuitDescription.forClass(ClassUnderTest)
                                                     .addClassDefinition(new StringDescription().acessor("name")
                                                                                                .maxSize(5))

        new TestScenario()
        TestScenario testCases = testSuitDescription.getTestScenarios()
        assert !testCases.isEmpty() : "A test suit with a class definition should generate test methods"

    }

    public static class ClassUnderTest{
        def nome
    }
}
