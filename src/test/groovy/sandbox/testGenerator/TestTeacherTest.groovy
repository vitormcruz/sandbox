package sandbox.testGenerator

import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
import sandbox.magritte.DescriptionMethod
import sandbox.testGenerator.magritte.DescriptionExtension

class TestTeacherTest {

    TestTeacher testTeacher = new TestTeacher()

    //TODO Ok, this should not be done like this
    @BeforeClass
    static void setupAll(){
        DescriptionExtension.classesForDescriptions.put(DescriptionForTest, DescriptionVisitorForTest)
    }

    @Test
    def void "Teach a class without description"(){
        testTeacher.teach(ClassWithoutDescription)
        assert ClassWithoutDescription.magritteTests.isEmpty()
    }

    public static class ClassWithoutDescription {}

    @Ignore("Will work when the tests of MagritteDescriptionFactory explore the case when a description is empty")
    @Test
    def void "Load class with an empty description"(){
        testTeacher.teach(ClassWithEmptyDescription)
        assert ClassWithEmptyDescription.magritteTests.isEmpty()
    }

    public static class ClassWithEmptyDescription {
        @DescriptionMethod
        def empty(){
        }
    }

    @Test
    def void "Load class with one description, each generating one test"(){
        testTeacher.teach(ClassWithADescription)
        assert ClassWithADescription.magritteTests.size == 1
    }

    public static class ClassWithADescription {
        @DescriptionMethod
        def myDescription(){
            return new DescriptionContainer(ClassUnderTest, [new DescriptionForTest()] as Description[])
        }
    }

    @Test
    def void "Load class with N descriptions, each generating one test"(){
        testTeacher.teach(ClassWithNDescriptions)
        assert ClassWithNDescriptions.magritteTests.size == 3
    }

    public static class ClassWithNDescriptions {
        @DescriptionMethod
        def myDescription(){
            return new DescriptionContainer(ClassUnderTest, [new DescriptionForTest(),
                                                             new DescriptionForTest(),
                                                             new DescriptionForTest()] as Description[])
        }
    }

    @Test
    def void "Load class with N descriptions, each generating N tests"(){
        DescriptionVisitorForTest.testScenarios = [new TestScenario(), new TestScenario()]
        testTeacher.teach(ClassWithNDescriptions)
        assert ClassWithNDescriptions.magritteTests.size == 6
        DescriptionVisitorForTest.testScenarios = [new TestScenario()]
    }

    public static class ClassUnderTest{}
}
