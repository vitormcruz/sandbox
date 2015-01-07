package sandbox.magritte.testGenerator.junit

import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.description.Description
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.testGenerator.SimpleTestScenario
import sandbox.magritte.testGenerator.TestTeacher
import sandbox.magritte.testGenerator.description.TestDescription
import sandbox.magritte.testGenerator.junit.scenarioGenerator.util.DescriptionForTest
import sandbox.magritte.testGenerator.junit.scenarioGenerator.util.TestGeneratorForTestDescriptionPartial

import static groovy.test.GroovyAssert.shouldFail
import static sandbox.magritte.description.builder.DescriptionFactory.New

class TestTeacherTest {

    TestTeacher testTeacher = new TestTeacher(TestGeneratorForTestDescriptionPartial)

    @Test
    def void "Create a teacher class without specifying TesteGenerator for TestDescription"(){
        def ex = shouldFail IllegalArgumentException, {new TestTeacher(null)}
        assert ex.message == "You should provide a TestGenerator implementation class"
    }

    @Test
    @Ignore
    def void "Create a teacher class with a TesteGenerator for TestDescription that do have default constructor"(){}


    @Test
    @Ignore
    def void "Create a teacher class with a class that is not a TesteGenerator for TestDescription"(){}

    @Test
    def void "Teach a class without description"(){
        def testMethodosTeached = testTeacher.teach(ClassWithoutDescription)
        assert testMethodosTeached.isEmpty()
    }

    public static class ClassWithoutDescription {}

    @Test
    def void "Load class with an empty description"(){
        def testMethodosTeached = testTeacher.teach(ClassWithEmptyDescription)
        assert testMethodosTeached.isEmpty()
    }

    public static class ClassWithEmptyDescription {
        @DescriptionModelDefinition
        def empty(){
        }
    }

    @Test
    def void "Load class with one description, each generating one test"(){
        def testMethodosTeached = testTeacher.teach(ClassWithADescription)
        assert testMethodosTeached.size() == 1
    }

    public static class ClassWithADescription {
        @DescriptionModelDefinition
        def myDescription(){
            return New(TestDescription).descriptionsFor(ClassUnderTest, [new DescriptionForTest()] as Description[])
        }
    }

    @Test
    def void "Load class with N descriptions, each generating one test"(){
        def testMethodosTeached = testTeacher.teach(ClassWithNDescriptions)
        assert testMethodosTeached.size() == 3
    }

    public static class ClassWithNDescriptions {
        @DescriptionModelDefinition
        def myDescription(){
            return New(TestDescription).descriptionsFor(ClassUnderTest, [new DescriptionForTest(),
                                                             new DescriptionForTest(),
                                                             new DescriptionForTest()] as Description[])
        }
    }

    @Test
    def void "Load class with N descriptions, each generating N tests"(){
        def aux = DescriptionForTest.testScenarios
        DescriptionForTest.testScenarios = [new SimpleTestScenario(UUID.randomUUID().toString(), {}),
                                                   new SimpleTestScenario(UUID.randomUUID().toString(), {})]
        def testMethodosTeached = testTeacher.teach(ClassWithNDescriptions)
        assert testMethodosTeached.size() == 6
        DescriptionForTest.testScenarios = aux
    }

    public static class ClassUnderTest{}


    //TODO the list of test methods must have unique method names.

}
