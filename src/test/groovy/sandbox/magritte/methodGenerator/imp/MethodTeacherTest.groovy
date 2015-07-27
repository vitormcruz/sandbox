package sandbox.magritte.methodGenerator.imp

import org.junit.Ignore
import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail
//TODO review this class
class MethodTeacherTest {

    MethodTeacher testTeacher = new MethodTeacher()

    @Test
    def void "Teach a class without specifying generated methods"(){
        def ex = shouldFail IllegalArgumentException, {new MethodTeacher().teach(ClassToBeTeach.newInstance(), null)}
        assert ex.message == "You should provide a non null collection of methods to teach"
    }

    @Test
    @Ignore
    def void "Teach a class without specifying a class to be teach"(){}

    @Test
    @Ignore
    def void "Teach a class specifying a collection of methods with non unique names"(){}

    @Test
    def void "Teach a class with empty method to teach collection"(){
        def methodsTeach = testTeacher.teach(ClassToBeTeach.newInstance(), [] as Collection)
        assert methodsTeach.isEmpty()
    }

    @Test
    def void "Teach one method"(){
        def methodsTeach = testTeacher.teach(ClassToBeTeach.newInstance(), [new SimpleGeneratedMethod("test", {return true})])
        assert methodsTeach.size() == 1
        assert new ClassToBeTeach().test()
    }

    @Test
    def void "Teach N methods"(){
        def generatedMethods = [
                new SimpleGeneratedMethod("test1", {return true}),
                new SimpleGeneratedMethod("test2", {return false}),
                new SimpleGeneratedMethod("test3", {return 42})
        ]

        def testMethodosTeached = testTeacher.teach(ClassToBeTeach.newInstance(), generatedMethods)
        assert testMethodosTeached.size() == 3
        assert new ClassToBeTeach().test1()
        assert !(new ClassToBeTeach().test2())
        assert new ClassToBeTeach().test3() == 42
    }

    public static class ClassToBeTeach {}
}
