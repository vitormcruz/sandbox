package sandbox.magritte.methodGenerator.imp
import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.description.Description
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.testGenerator.description.TestDescription
import sandbox.magritte.testGenerator.util.DescriptionForTest
import sandbox.magritte.testGenerator.util.TestGeneratorForTestDescriptionPartial

import static groovy.test.GroovyAssert.shouldFail
import static sandbox.magritte.description.builder.DescriptionFactory.New

//TODO review this class
class MethodTeacherMethod {

    MethodTeacher testTeacher = new MethodTeacher(TestGeneratorForTestDescriptionPartial)

    @Test
    def void "Create a teacher class without specifying MethodGenerator for TestDescription"(){
        def ex = shouldFail IllegalArgumentException, {new MethodTeacher(null)}
        assert ex.message == "You should provide a TestGenerator implementation class"
    }

    @Test
    @Ignore
    def void "Create a teacher class with a TesteGenerator for MethodGenerator that do have default constructor"(){}


    @Test
    @Ignore
    def void "Create a teacher class with a class that is not a MethodGenerator for TestDescription"(){}

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
        DescriptionForTest.testScenarios = [new SimpleGeneratedMethod(UUID.randomUUID().toString(), {}),
                                                   new SimpleGeneratedMethod(UUID.randomUUID().toString(), {})]
        def testMethodosTeached = testTeacher.teach(ClassWithNDescriptions)
        assert testMethodosTeached.size() == 6
        DescriptionForTest.testScenarios = aux
    }

    public static class ClassUnderTest{}


    //TODO the list of test methods must have unique method names.

}
