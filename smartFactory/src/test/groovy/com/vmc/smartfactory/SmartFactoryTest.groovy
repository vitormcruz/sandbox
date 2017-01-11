package com.vmc.smartfactory

import org.apache.commons.lang.StringUtils
import org.junit.Before
import org.junit.Test

class SmartFactoryTest {

    private SmartFactory smartFactory

    @Before
    def void setup(){
        smartFactory = new SmartFactory()
    }

    @Test
    def void "Test getConfiguration"(){
        assert smartFactory.configurationFor("a").is(smartFactory.configurationFor("a"))
        assert !smartFactory.configurationFor("ab").is(smartFactory.configurationFor("a"))
        assert !smartFactory.configurationFor("a.b").is(smartFactory.configurationFor("a"))
        assert !smartFactory.configurationFor("a.*").is(smartFactory.configurationFor("a"))
        assert smartFactory.configurationFor("a.*").is(smartFactory.configurationFor("a.*"))
        assert !smartFactory.configurationFor("a.*").is(smartFactory.configurationFor("a.**"))
    }

    @Test
    def void "Get instances of classes not configured must return null"(){
        def testCases = [[class:String, expectedResult: null],
                         [class:Integer, expectedResult: null],
                         [class:Map, expectedResult: null]]

        testCases.each {
            assert smartFactory.instanceForCallerOf(SmartFactoryTest, it.class) == it.expectedResult
        }
    }

    @Test
    def void "Get instances of classes configured for a single class"(){
        smartFactory.configurationFor("com.vmc.smartfactory.SmartFactoryTest").put(String, "ok")
        smartFactory.configurationFor("com.vmc.smartfactory.SmartFactoryTest").put(Integer, 1)
        def someMap = [:]
        smartFactory.configurationFor("com.vmc.smartfactory.SmartFactoryTest").put(Map, someMap)
        def testCases = [[class:String, expectedResult: "ok"],
                         [class:Integer, expectedResult: 1],
                         [class:Map, expectedResult: someMap]]

        testCases.each {
            assert smartFactory.instanceForCallerOf(SmartFactoryTest, it.class) == it.expectedResult
        }
    }

    @Test
    def void "Get instances of a class with broad configuration"(){
        smartFactory.configurationFor("com.**").put(String, "ok")
        smartFactory.configurationFor("java.**").put(String, "fine")
        assert smartFactory.instanceForCallerOf(SmartFactoryTest, String) == "ok"
        assert smartFactory.instanceForCallerOf(Integer, String) == "fine"
        assert smartFactory.instanceForCallerOf(StringUtils, String) == null
    }

    @Test
    def void "Get instances of classes using two different configurations"(){
        smartFactory.configurationFor("com.vmc.smartfactory.SmartFactoryTest").put(String, "ok")
        smartFactory.configurationFor("com.vmc.smartfactory.SmartFactoryTest").put(Integer, 1)
        def someMap = [:]
        smartFactory.configurationFor("com.vmc.smartfactory.SmartFactoryTest").put(Map, someMap)
        def testCases = [[class:String, expectedResult: "ok"],
                         [class:Integer, expectedResult: 1],
                         [class:Map, expectedResult: someMap]]

        testCases.each {
            assert smartFactory.instanceForCallerOf(SmartFactoryTest, it.class) == it.expectedResult
        }
    }

    @Test
    def void "Get instances of a class configured with a closure"(){
        def numberStream = [1,2,3].iterator()
        smartFactory.configurationFor("com.**").put(Integer, {return numberStream.next()})
        assert smartFactory.instanceForCallerOf(SmartFactoryTest, Integer) == 1
        assert smartFactory.instanceForCallerOf(SmartFactoryTest, Integer) == 2
        assert smartFactory.instanceForCallerOf(SmartFactoryTest, Integer) == 3

    }

}
