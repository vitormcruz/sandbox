package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.magritte.description.BaseDescription
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

import static org.hamcrest.CoreMatchers.hasItem
import static org.junit.Assert.assertThat

abstract class JunitTestGeneratorForBaseDescription implements BaseDescription, MethodGenerator {

    def protected testScenarios = []
    def protected String accessor
    def protected Class describedClass

    @Override
    Description beRequired() {
        String error = "${describedClass.getName().toLowerCase()}.validation.${accessor}.mandatory.error"

        testScenarios.add(new SimpleGeneratedMethod("The ${accessor} of ${describedClass.getSimpleName()} is required. " +
                                                    "Testing providing null for it.",
                {
                    def testSubject = describedClass.newInstance()
                     testSubject."${accessor}" = null
                     def result = testSubject.validate()
                     assertThat(result.getFailures().collect {it.getException().getMessage()}, hasItem(error))
                }))
        return this
    }

    @Override
    public Collection<GeneratedMethod> getGeneratedMethods() {
        return testScenarios
    }
}
