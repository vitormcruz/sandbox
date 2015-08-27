package sandbox.magritte.methodGenerator.imp
import org.junit.Test
import sandbox.magritte.methodGenerator.MethodGenerator

class ComposedMethodGeneratorTest {

    @Test
    def void "Composed generator should return the generated methods returned from every item of its composition"(){
        getTestCases().each { testCase ->
            def composedMethodGenerator = new ComposedMethodGenerator()
            testCase.methodGenerators.each {composedMethodGenerator.addMethodGenerator(it)}
            assert composedMethodGenerator.generatedMethods.methodName as Set == testCase.expectedGeneratedMethods as Set
        }
    }

    private ArrayList<LinkedHashMap<String, ArrayList>> getTestCases() {
        [
            [methodGenerators: [], expectedGeneratedMethods: []],

            [methodGenerators: [getMethodGeneratorGenerating()], expectedGeneratedMethods: []],

            [methodGenerators: [getMethodGeneratorGenerating(), getMethodGeneratorGenerating()], expectedGeneratedMethods: []],

            [methodGenerators: [getMethodGeneratorGenerating("test1")], expectedGeneratedMethods: ["test1"]],

            [methodGenerators: [getMethodGeneratorGenerating("test1", "test2")], expectedGeneratedMethods: ["test1", "test2"]],

            [methodGenerators: [getMethodGeneratorGenerating("test1", "test2"),
                                getMethodGeneratorGenerating("test3", "test4")],
             expectedGeneratedMethods: ["test1", "test2", "test3", "test4"]]
        ]
    }

    private SimpleGeneratedMethod getGeneratedMethodNamed(methodName) {
        return new SimpleGeneratedMethod(methodName, {})
    }

    private MethodGenerator getMethodGeneratorGenerating(String... generatedMethods) {
        return [getGeneratedMethods: { generatedMethods.collect {getGeneratedMethodNamed(it)}}] as MethodGenerator
    }
}
