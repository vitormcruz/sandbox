package sandbox.testGenerator

import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.Description
import sandbox.magritte.DescriptionMethod
import sandbox.testGenerator.magritte.model.TestDescription

import static sandbox.magritte.DescriptionFactory.New

class TestTeacherTest {

    TestTeacher testTeacher = new TestTeacher()

    @Test
    def void "Teach a class without description"(){
        def testMethodosTeached = testTeacher.teach(ClassWithoutDescription)
        assert testMethodosTeached.isEmpty()
    }

    public static class ClassWithoutDescription {}

    @Ignore("Will work when the tests of MagritteDescriptionFactory explore the case when a description is empty")
    @Test
    def void "Load class with an empty description"(){
        def testMethodosTeached = testTeacher.teach(ClassWithEmptyDescription)
        assert testMethodosTeached.isEmpty()
    }

    public static class ClassWithEmptyDescription {
        @DescriptionMethod
        def empty(){
        }
    }

    @Test
    def void "Load class with one description, each generating one test"(){
        def testMethodosTeached = testTeacher.teach(ClassWithADescription)
        assert testMethodosTeached.size() == 1
    }

    public static class ClassWithADescription {
        @DescriptionMethod
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
        @DescriptionMethod
        def myDescription(){
            return New(TestDescription).descriptionsFor(ClassUnderTest, [new DescriptionForTest(),
                                                             new DescriptionForTest(),
                                                             new DescriptionForTest()] as Description[])
        }
    }

    @Test
    def void "Load class with N descriptions, each generating N tests"(){
        def aux = DescriptionForTest.testScenarios
        DescriptionForTest.testScenarios = [new TestScenario(UUID.randomUUID().toString(), {}),
                                                   new TestScenario(UUID.randomUUID().toString(), {})]
        def testMethodosTeached = testTeacher.teach(ClassWithNDescriptions)
        assert testMethodosTeached.size() == 6
        DescriptionForTest.testScenarios = aux
    }

    public static class ClassUnderTest{}


    //TODO the list of test methods must have unique method names.

}
