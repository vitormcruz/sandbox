package sandbox.magritte.testGenerator

import org.apache.commons.lang.StringUtils
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat
/**
 */
class MandatoryTestGenerator implements MethodGenerator {

    private Class classUnderTest
    private requiredAccessors = []
    def protected testScenarios = []

    public void setClassUnderTest(Class classUnderTest) {
        this.classUnderTest = classUnderTest
    }

    def requiredAccessor(Object requiredAccessor) {
        requiredAccessors.add(requiredAccessor)
        String error = "${classUnderTest.getName().toLowerCase()}.validation.${requiredAccessor}.mandatory.error"

        testScenarios.add(new SimpleGeneratedMethod("The ${requiredAccessor} of ${classUnderTest.getSimpleName()} is required. " +
                                                    "Testing providing null for it.",
                {
                    def testSubject = classUnderTest.newInstance()
                     testSubject."${requiredAccessor}" = null
                     def result = testSubject.validate()
                     assertThat(result.getFailures().collect {it.getException().getMessage()}, hasItem(error))
                }))
    }

    @Override
    public Collection<GeneratedMethod> getGeneratedMethods() {
        if (requiredAccessors.isEmpty()) return Collections.emptyList()

        def finalRequiredTests = []
        finalRequiredTests.addAll(testScenarios)

        finalRequiredTests.add(new SimpleGeneratedMethod("Testing providing valid values to all required accessors (${StringUtils.join(requiredAccessors, ", ")}) ",
                {
                    def testSubject = classUnderTest.newInstance()
                    requiredAccessors.each { testSubject."$it" = "test"} //TODO how to get a valid value here?
                    def result = testSubject.validate()
                    requiredAccessors.each {
                        def error = "${classUnderTest.getSimpleName().toLowerCase()}.validation.${it}.mandatory.error"
                        assertThat(result.getFailures().collect {it.getException().getMessage()}, not(hasItem(error)))
                    }
                }))



        return finalRequiredTests
    }
}
