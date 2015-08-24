package sandbox.magritte.testGenerator

import org.apache.commons.lang.StringUtils
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

//TODO must refactor with MandatoryTestGeneratorForMethod
class MandatoryTestGeneratorForMethod extends MandatoryTestGenerator{
    private Class classUnderTest
    private String methodUnderTest
    private requiredParams = []
    def protected testScenarios = []
    protected Closure validationMethod

    Class getClassUnderTest() {
        return classUnderTest
    }

    public void setClassUnderTest(Class classUnderTest) {
        this.classUnderTest = classUnderTest
    }

    String getMethodUnderTest() {
        return methodUnderTest
    }

    void setMethodUnderTest(String methodUnderTest) {
        this.methodUnderTest = methodUnderTest
    }

    void setValidationMethod(Closure validationMethod){
        this.validationMethod = validationMethod
    }

    @Override
    def requiredAccessor(requiredParam) {
        this.requiredParams.add(requiredParam)
    }

    @Override
    public Collection<GeneratedMethod> getGeneratedMethods() {
        if (requiredParams.isEmpty()) return Collections.emptyList()


        requiredParams.eachWithIndex { it, index ->

            def params = new Object[requiredParams.size()]
            String error = "${classUnderTest.getName().toLowerCase()}.validation.${it}.mandatory.error" //TODO should use the method name, but I will have to change validatin as well

            testScenarios.add(new SimpleGeneratedMethod("The ${it} parameter of ${classUnderTest.getSimpleName()}#${methodUnderTest} is required. " +
                    "Testing providing null for it.",
                    {
                        def result = validationMethod(params)
                        assertThat(result, hasItem(error))
                    }))

        }


        def finalRequiredTests = []
        finalRequiredTests.addAll(testScenarios)

        finalRequiredTests.add(new SimpleGeneratedMethod("Testing providing valid values to all required params of ${classUnderTest.getSimpleName()}#${methodUnderTest}(${StringUtils.join(requiredParams, ", ")}) ",
                {
                    def result = validationMethod(requiredParams.collect{1}) //TODO how to get a valid value here?
                    requiredParams.each {
                        def error = "${classUnderTest.getName().toLowerCase()}.validation.${it}.mandatory.error"
                        assertThat(result, not(hasItem(error)))
                    }
                }))



        return finalRequiredTests
    }

}
