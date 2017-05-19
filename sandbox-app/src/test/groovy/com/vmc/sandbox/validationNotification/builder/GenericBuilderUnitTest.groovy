package com.vmc.sandbox.validationNotification.builder

import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import com.vmc.sandbox.validationNotification.builder.imp.UsedForbiddenConstructor
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Test

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError
import static groovy.test.GroovyAssert.shouldFail
import static org.junit.Assert.fail

class GenericBuilderUnitTest extends ValidationNotificationTestSetup{

    @Test
    def void "The GenericBuilder class parameter must be provided"(){
        def ex = shouldFail IllegalArgumentException, {getBuilderFor(null)}
        assert ex.message == "A class to build must be provided"
    }


    @Test
    def void "Call onSuccessDoWithBuiltEntity with a success built entity "(){
        def buildersForTest = getSuccessBuilders()
        buildersForTest.each { builderForTest ->
            def builtEntity
            builderForTest.buildAndDoOnSuccess { builtEntity = it }
            assert builtEntity != null : "Upon success, entity should not be null"
            assert builtEntity.getAttribute() == "ok"
        }
    }

    @Test
    def void "Call onSuccessDoWithBuiltEntity with a failed built entity "(){
        def buildersForTest = getFailBuilders()
        buildersForTest.each { builderForTest ->
            builderForTest.buildAndDoOnSuccess { fail("Issued success but the entity was created with an error.") }
            assert builderForTest.build() == null: "null should be returned upon build failure"
        }
    }

    @Test
    def void "Call onSuccessDoWithBuiltEntity should return null with a failed built entity"(){
        def buildersForTest = getFailBuilders()
        buildersForTest.each { builderForTest ->
            def result = builderForTest.buildAndDoOnSuccess {}
            assert result == null: "Call onSuccessDoWithBuiltEntity should return null with a failed built entity"
        }
    }

    @Test
    def void "Call onSuccessDoWithBuiltEntity should return the built entity when build was successful"(){
        def buildersForTest = getSuccessBuilders()
        buildersForTest.each { builderForTest ->
            def result = builderForTest.buildAndDoOnSuccess {}
            assert result != null: "Call onSuccessDoWithBuiltEntity should return the built entity when build was successful"
            assert result.getAttribute() == "ok"
        }
    }

    @Test
    def void "Call buildAndDo with a success built entity"(){
        def buildersForTest = getSuccessBuilders()
        buildersForTest.each { builderForTest ->
            builderForTest.buildAndDo({ assert it.getAttribute() == "ok" },
                    { fail("Issued success but the entity was created with an error.") })
        }
    }

    @Test
    def void "Call buildAndDoOnSuccess with a failed built entity "(){
        def buildersForTest = getFailBuilders()
        buildersForTest.each { builderForTest ->
            builderForTest.buildAndDoOnSuccess { fail("Issued success but the entity was created with an error.") }
            assert builderForTest.build() == null: "null should be returned upon build failure"
            def result = builderForTest.buildAndDo({
                fail("Issued success but the entity was created with an error.")
            }, {})
            assert result == null: "In case of error the builder should return null."
        }
    }

    @Test
    def void "Call buildAndDo should return null with a failed built entity"(){
        def buildersForTest = getFailBuilders()
        buildersForTest.each { builderForTest ->
            def result = builderForTest.buildAndDo({}, {})
            assert result == null: "Call buildAndDo should return null with a failed built entity"
        }
    }

    @Test
    def void "Call buildAndDo should return the built entity when build was successful"(){
        def buildersForTest = getSuccessBuilders()
        buildersForTest.each { builderForTest ->
            def result = builderForTest.buildAndDo({}, {})
            assert result != null: "Call buildAndDo should return the built entity when build was successful"
            assert result.getAttribute() == "ok"
        }
    }

    @Test
    def void "Using one with to call constructor in the correct order"(){
        def entity = getBuilderFor(TestNPropertiesOneConstructor).with("a", "b", 1, 1L).build()
        assert entity != null
        assert entity instanceof TestNPropertiesOneConstructor
    }

    @Test
    def void "Using one with to call constructor in the wrong order"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).with("a", 1, 1L, "b").build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "Using one with to call constructor using less arguments than the constructor has"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).with("a", "b", 1).build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "Using one with to call constructor using more arguments than the constructor has"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).with("a", "b", 1, 1L, "c").build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "N withs in the correct order for K arguments, where N <> K"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).withA("a")
                                                                                                          .withRest("b", 1, 1L, "c")
                                                                                                          .build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "N withs in the correct order for K arguments, where N == K"(){
        def object = getBuilderFor(TestNPropertiesOneConstructor).withA("a")
                                                                 .withRest("b", 1, 1L)
                                                                 .build()
        assert object != null
        assert object instanceof TestNPropertiesOneConstructor
    }

    @Test
    def void "N withs in the incorrect order for K arguments, where N == K"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).with1(1)
                                                                                                          .withRest("a", "b", 1L)
                                                                                                          .build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "Repeat with giving wrong arguments"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).with("a")
                                                                                                          .with(1, 1L)
                                                                                                          .build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "Repeat with giving right arguments"(){
        def entity = getBuilderFor(TestNPropertiesOneConstructor).with("a", "b").with(1, 1L).build()
        assert entity != null
        assert entity instanceof TestNPropertiesOneConstructor
    }

    @Test
    def void "Mixing with and set"(){
        TestNPropertiesOneConstructor object = getBuilderFor(TestNPropertiesOneConstructor).with("a", "b")
                                                                                           .with(1, 1L)
                                                                                           .setA("Changed")
                                                                                           .build()

        assert object.getA() == "Changed"
    }

    @Test
    def void "Repeat set"(){
        TestNPropertiesOneConstructor object = getBuilderFor(TestNPropertiesOneConstructor).with("a", "b")
                                                                                           .with(1, 1L)
                                                                                           .setA("Changed")
                                                                                           .setB("Changed")
                                                                                           .setB("Changed2")
                                                                                           .build()

        assert object.getA() == "Changed"
        assert object.getB() == "Changed2"
    }

    @Test
    def void "Builder should not call forbidden constructors"(){
        def ex = shouldFail UsedForbiddenConstructor, {getBuilderFor(TestForbiddenConstructor).build()}
        assert ex.message == "The constructor found for TestForbiddenConstructor with [] arguments is of forbidden use."
        ex = shouldFail UsedForbiddenConstructor, {getBuilderFor(TestForbiddenConstructor).withString("test").build()}
        assert ex.message == "The constructor found for TestForbiddenConstructor with [String] arguments is of forbidden use."
    }

    @Test
    def void "Builder should not call forbidden constructors, even with null arguments"(){
        def ex = shouldFail UsedForbiddenConstructor, {getBuilderFor(TestForbiddenConstructor).withString(null).build()}
        assert ex.message == "The constructor found for TestForbiddenConstructor with [NullObject] arguments is of forbidden use."
    }

    @Test
    def void "Builder should work with all allowed constructors"(){
        [Integer, Byte, Long].forEach {
            def generatedEntity = getBuilderFor(TestForbiddenConstructor).with(it.valueOf("1")).build()
            assert generatedEntity != null : "Entity should be build for constructor with ${it.simpleName}, but wasn't"
        }
    }

    @Test
    def void "Builder used for constructor passing one null argument"(){
        def entity = getBuilderFor(TestConstructorWithNull).withName(null).build()
        assert entity != null : "When null is used, a constructor with the same number of arguments should be used"
    }

    @Test
    def void "Builder used for constructor passing N null arguments"(){
        def entity = getBuilderFor(TestConstructorWithNull).withName(null)
                                                           .withAddress(null)
                                                           .build()
        assert entity != null : "When null is used, a constructor with the same number of arguments should be used"
    }

    def getSuccessBuilders(){
        return [getBuilderFor(TestEntity).setAttribute("ok"),
                getBuilderFor(TestConstructorWithOneArgument).withAttribute("ok")]
    }

    def getFailBuilders(){
        return [getBuilderFor(TestEntity).setAttribute("fail"),
                getBuilderFor(TestConstructorWithOneArgument).withAttribute("fail")]
    }

    public GenericBuilder getBuilderFor(Class<TestEntity> clazz) {
        new GenericBuilder(clazz)
    }

    public static class TestEntity{
        def attribute;

        void setAttribute(attribute) {
            if(attribute == "fail"){
                issueError(this, [:], "error")
            }else{
                this.attribute = attribute
            }
        }
    }

    public static class TestConstructorWithOneArgument {
        def String attribute;

        public TestConstructorWithOneArgument(String attribute) {
            if(attribute == "fail"){
                issueError(this, [:], "error")
            }else{
                this.attribute = attribute
            }
        }
    }

    public static class TestConstructorWithNArguments {
        def String attribute;
        def String attribute2;
        def String attribute3;

        public TestConstructorWithNArguments(String attribute, String attribute2, String attribute3) {
            this.attribute = attribute
            this.attribute2 = attribute2
            this.attribute3 = attribute3
        }
    }

    public static class TestForbiddenConstructor implements BuilderAwareness{
        private TestForbiddenConstructor(){invalidForBuilder()}
        private TestForbiddenConstructor(String a){invalidForBuilder()}
        protected TestForbiddenConstructor(Integer a){}
        public TestForbiddenConstructor(Byte a){}

        TestForbiddenConstructor(Long a){}
    }

    public static class TestConstructorWithNull {
        TestConstructorWithNull(String a){}
        TestConstructorWithNull(String a, String b){}
    }

    public static class TestNPropertiesOneConstructor {
        def String a
        def String b
        def int c
        def long d

        TestNPropertiesOneConstructor(String a, String b, Integer c, Long d){
            this.d = d
            this.c = c
            this.b = b
            this.a = a
        }
    }

}
