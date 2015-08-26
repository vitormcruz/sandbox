package sandbox.magritte.testGenerator.junit

import groovy.test.GroovyAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runners.model.InitializationError
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.smartfactory.SmartFactory

import static org.mockito.Matchers.anyObject
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class JUnit4TestGeneratorRunnerTest {

    private TestGenerator testGeneratorMock

    @Before
    def void setup(){
        testGeneratorMock = mock(TestGenerator)
        SmartFactory.configureForTest().configurationFor(JUnit4TestGeneratorRunner).put(TestGenerator, testGeneratorMock)
    }

    @After
    def void cleanup(){
        SmartFactory.resetConfigForTest()
    }

    @Test
    def void "If there are no tests, static or dynamic, an error is expected"(){
        mockTestGeneratorToReturm([])
        GroovyAssert.shouldFail (InitializationError, {new JUnit4TestGeneratorRunner(TestClassWithNoTests)})
    }

    @Test
    def void "computeTestMethods should return the correct list of methods"(){
        getTestCases().each { testCase ->
            mockTestGeneratorToReturm(testCase.generatedMethodsNames)
            def runner = new JUnit4TestGeneratorRunner(testCase.testClass)
            def actualTestNames = runner.computeTestMethods().collect {it.name}

            assert testCase.expectedMethodNames as Set == actualTestNames as Set
        }
    }

    def getTestCases() {
        return [
                [generatedMethodsNames:  ["generatedTest1"], testClass: TestClassWithNoTests,
                    expectedMethodNames: ["generatedTest1"]],

                [generatedMethodsNames:  ["generatedTest1", "generatedTest2"], testClass: TestClassWithNoTests,
                    expectedMethodNames: ["generatedTest1", "generatedTest2"]],

                [generatedMethodsNames:  ["generatedTest1", "generatedTest2"], testClass: TestClassWithALotOfTests,
                    expectedMethodNames: ["staticTest1", "staticTest2", "staticTest3", "generatedTest1", "generatedTest2"]]
               ]
    }

    private void mockTestGeneratorToReturm(testNames) {
        when(testGeneratorMock.getGeneratedMethodsFor(anyObject())).thenReturn(getGeneratedMethodsFrom(testNames))
    }

    private ArrayList<SimpleGeneratedMethod> getGeneratedMethodsFrom(testsNames) {
        return testsNames.collect {new SimpleGeneratedMethod(it, {})}
    }

    public static class TestClassWithNoTests { }

    public static class TestClassWithALotOfTests {

        @Test
        def void staticTest1(){}

        @Test
        def void staticTest2(){}

        @Test
        def void staticTest3(){}

    }

}
