package testGenerator
import sandbox.magritte.StringDescription
import org.junit.Ignore
import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail
/**
 * Tests the description of test suits.
 */
class TestSuitDescriptionTest{

    @Test
    def void "Create a test description for null class"(){
        def ex = shouldFail(IllegalArgumentException, { TestSuitDescription.forClass(null) })
        assert ex.message == "Cannot create a test suit description for null class"
    }

    @Test
    def void "Create a test description without specify any definition for the class under test"(){
        def testSuitDescription = TestSuitDescription.forClass(ClassUnderTest)
        assert testSuitDescription.getTestScenarios().isEmpty() : "No definition test suit description should not generate any test methods"
    }

    @Ignore("Thinking how to implement the TestScenario class")
    @Test
    def void "Create a test description specifying one definition for the class under test"(){
        def testSuitDescription = TestSuitDescription.forClass(ClassUnderTest)
                                                     .addClassDefinition(new StringDescription().acessor("name")
                                                                                                .beRequired())

        new TestScenario()
        TestScenario testCases = testSuitDescription.getTestScenarios()
        assert !testCases.isEmpty() : "A test suit with a class definition should generate test methods"

    }

    public static class ClassUnderTest{
        def nome
    }
}
