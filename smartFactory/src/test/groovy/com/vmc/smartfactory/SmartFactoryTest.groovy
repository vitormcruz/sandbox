package com.vmc.smartfactory

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
    def void "Get instances of classes not confired must return null"(){
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

}
