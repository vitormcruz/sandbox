package sandbox.testGenerator
import org.junit.BeforeClass
import org.junit.Test
import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
import sandbox.testGenerator.magritte.DescriptionExtension

import static groovy.test.GroovyAssert.shouldFail
/**
 * Tests the description of test suits.
 */
class GenerateTestFromDescriptionTest {

    //TODO Ok, this should not be done like this
    @BeforeClass
    static void setupAll(){
        DescriptionExtension.classesForDescriptions.put(DescriptionForTest, DescriptionVisitorForTest)
        DescriptionExtension.classesForDescriptions.put(DescriptionForTest2, DescriptionVisitorForTest2)
    }

    @Test
    def void "Create a test scenarios for null class"(){
        def description = new DescriptionContainer(null, [] as Description[])
        def ex = shouldFail(IllegalArgumentException, { description.asTestScenarios() })
        assert ex.message == "Cannot create test scenarios for a DescriptionContainer that do not specify a target class"
    }

    @Test
    def void "Create a test scenarios without specify any definition for the class under test"(){
        def description = new DescriptionContainer(ClassUnderTest, [] as Description[])
        assert description.asTestScenarios().isEmpty() : "No definition test suit description should not generate any test methods."
    }

    @Test
    def void "Create a test description specifying one definition for the class under test"(){
        def description = new DescriptionContainer(ClassUnderTest, new DescriptionForTest())

        def testCases = description.asTestScenarios()
        assert DescriptionVisitorForTest.testScenarios as Set == testCases as Set : "A test suit with a class definition should generate test methods based on the description used."
    }

    @Test
    def void "Create a test description specifying N definitions for the class under test"(){
        def description = new DescriptionContainer(ClassUnderTest, new DescriptionForTest(), new DescriptionForTest2())
        def testScenariosEsperados = []
        testScenariosEsperados.addAll(DescriptionVisitorForTest2.testScenarios)
        testScenariosEsperados.addAll(DescriptionVisitorForTest.testScenarios)

        def testCases = description.asTestScenarios()
        assert testScenariosEsperados as Set == testCases as Set : "A test suit with a class definition should generate test methods based on the descriptions used."
    }

    public static class ClassUnderTest{
        def nome
    }

    //TODO reduce size of these classes

    public static class DescriptionForTest implements Description{

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

        def accept(visitor){

        }
    }

    public static class DescriptionVisitorForTest implements Description{
        static def testScenarios = [new TestScenario()]

        DescriptionVisitorForTest(Class forClass) {
        }

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

        def getTestScenarios(){
            return testScenarios
        }
    }


    public static class DescriptionForTest2 implements Description{

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

        def accept(visitor){

        }
    }

    public static class DescriptionVisitorForTest2 implements Description{
        static def testScenarios = [new TestScenario()]

        DescriptionVisitorForTest2(Class forClass) {
        }

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

        def getTestScenarios(){
            return testScenarios
        }
    }
}
