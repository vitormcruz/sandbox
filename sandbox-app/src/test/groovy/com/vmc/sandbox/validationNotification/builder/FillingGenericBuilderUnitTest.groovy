package com.vmc.sandbox.validationNotification.builder

import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Test

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError
import static groovy.test.GroovyAssert.shouldFail

class FillingGenericBuilderUnitTest extends ValidationNotificationTestSetup{

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
                                                                 .withB("b")
                                                                 .withC(1)
                                                                 .withD(1L)
                                                                 .build()
        assert object != null
        assert object instanceof TestNPropertiesOneConstructor
    }

    @Test
    def void "N withs in the incorrect order for K arguments, where N == K"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).with1(1)
                                                                                                          .withA("a")
                                                                                                          .withB("b")
                                                                                                          .withC(1L)
                                                                                                          .build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "Repeat with giving wrong arguments"(){
        def error = shouldFail GroovyRuntimeException, {getBuilderFor(TestNPropertiesOneConstructor).with("a")
                                                                                                          .withA(1)
                                                                                                          .withB(1L)
                                                                                                          .build()}
        assert error.message.contains("Could not find matching constructor")
    }

    @Test
    def void "Repeat with giving right arguments"(){
        def entity = getBuilderFor(TestNPropertiesOneConstructor).withA("a").withB("b").withC(1).withD(1L).build()
        assert entity != null
        assert entity instanceof TestNPropertiesOneConstructor
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

    @Test
    def void "Mixing with and set"(){
        TestNPropertiesOneConstructor object = getBuilderFor(TestNPropertiesOneConstructor).withA("a")
                                                                                           .withB("b")
                                                                                           .withC(1)
                                                                                           .withD(1L)
                                                                                           .setA("Changed")
                                                                                           .build()

        assert object.getA() == "Changed"
    }

    @Test
    def void "Repeat set"(){
        TestNPropertiesOneConstructor object = getBuilderFor(TestNPropertiesOneConstructor).withA("a")
                                                                                           .withB("b")
                                                                                           .withC(1)
                                                                                           .withD(1L)
                                                                                           .setA("Changed")
                                                                                           .setB("Changed")
                                                                                           .setB("Changed2")
                                                                                           .build()

        assert object.getA() == "Changed"
        assert object.getB() == "Changed2"
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
