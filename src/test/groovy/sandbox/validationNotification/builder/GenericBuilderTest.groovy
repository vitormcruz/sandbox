package sandbox.validationNotification.builder

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import sandbox.validationNotification.ApplicationValidationNotifier

import static org.junit.Assert.fail

class GenericBuilderTest {

    @BeforeClass
    def static void setup(){
        ApplicationValidationNotifier.createCurrentListOfListeners()
    }

    @AfterClass
    def static void tearDown(){
        ApplicationValidationNotifier.destroyCurrentListOfListeners()
    }

    @Test
    def void "Call onSuccessDoWithBuiltEntity with a success built entity "(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("ok")
        TestEntity builtEntity
        builderForTest.buildAndDoOnSuccess { builtEntity = it }
        assert builtEntity != null : "Upon success, entity should not be null"
        assert builtEntity.getAttribute() == "ok"
    }

    @Test
    def void "Call onSuccessDoWithBuiltEntity with a failed built entity "(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("fail")
        builderForTest.buildAndDoOnSuccess {fail("Issued success but the entity was created with an error.")}
        assert builderForTest.buildEntity() == null : "null should be returned upon build failure"
    }

    @Test
    def void "Call onSuccessDoWithBuiltEntity should return null with a failed built entity"(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("fail")
        def result = builderForTest.buildAndDoOnSuccess {}
        assert result == null : "Call onSuccessDoWithBuiltEntity should return null with a failed built entity"
    }

    @Test
    def void "Call onSuccessDoWithBuiltEntity should return the built entity when build was successful"(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("ok")
        def result = builderForTest.buildAndDoOnSuccess {}
        assert result != null : "Call onSuccessDoWithBuiltEntity should return the built entity when build was successful"
        assert result.getAttribute() == "ok"
    }

    @Test
    def void "Call buildAndDo with a success built entity"(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("ok")
        builderForTest.buildAndDo({ assert it.getAttribute() == "ok" },
                                  { fail("Issued success but the entity was created with an error.")})
    }

    @Test
    def void "Call buildAndDoOnSuccess with a failed built entity "(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("fail")
        builderForTest.buildAndDoOnSuccess {fail("Issued success but the entity was created with an error.")}
        assert builderForTest.buildEntity() == null : "null should be returned upon build failure"
        def result = builderForTest.buildAndDo({ fail("Issued success but the entity was created with an error.") }, { })
        assert result == null : "In case of error the builder should return null."
    }

    @Test
    def void "Call buildAndDo should return null with a failed built entity"(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("fail")
        def result = builderForTest.buildAndDo ({}, {})
        assert result == null : "Call buildAndDo should return null with a failed built entity"
    }

    @Test
    def void "Call buildAndDo should return the built entity when build was successful"(){
        def builderForTest = new GenericBuilder(TestEntity)
        builderForTest.setAttribute("ok")
        def result = builderForTest.buildAndDo ({}, {})
        assert result != null : "Call buildAndDo should return the built entity when build was successful"
        assert result.getAttribute() == "ok"
    }

    public class TestEntity{
        private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()
        def attribute;

        void setAttribute(attribute) {
            if(attribute == "fail"){
                notifier.issueError("error")
            }else{
                this.attribute = attribute
            }
        }

    }

}
