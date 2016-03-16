package sandbox.magritte.testGenerator

import sandbox.magritte.testGenerator.junit.scenarioGenerator.ValidationFactory

class TestContext {

    public static final Closure<Void> ILLEGAL_VALIDATION_METHOD = {throw new IllegalStateException("No validation method was specified")}
    private ValidationFactory validationFactory = ValidationFactory.smartNewFor(TestContext)
    private Class classUnderTest
    private Closure validationMethod
    private String methodUnderTest
    private String testTargetName
    private MandatoryTestGeneratorForMethod mandatoryTestGenerator

    public TestContext (Class classUnderTest, String methodUnderTest, Closure validationMethod, String testTargetName, MandatoryTestGeneratorForMethod mandatoryTestGenerator) {
        //TODO use validation framework
        this.testTargetName = testTargetName
        validate(classUnderTest)
        this.classUnderTest = classUnderTest
        this.methodUnderTest = methodUnderTest
        this.validationMethod = shouldCreateNewValidation(validationMethod, methodUnderTest) ? validationFactory.getValidationMethodFor(methodUnderTest, classUnderTest) :
                                                                                               validationMethod
        this.mandatoryTestGenerator = mandatoryTestGenerator
    }

    public TestContext(Class classUnderTest, String methodUnderTest, Closure validationMethod) {
        this(classUnderTest, methodUnderTest, validationMethod, methodUnderTest)
    }

    private boolean shouldCreateNewValidation(Closure validationMethod, String methodUnderTest) {
        return validationMethod == ILLEGAL_VALIDATION_METHOD && methodUnderTest != null
    }

    private void validate(Class classUnderTest) {
        if (classUnderTest == null) {
            throw new IllegalArgumentException("Cannot create test scenarios for a TestDescription that do not specify a target class")
        }
    }

    public TestContext (Class classUnderTest, String methodUnderTest) {
        this(classUnderTest, methodUnderTest, ILLEGAL_VALIDATION_METHOD, testTargetName, mandatoryTestGenerator)
    }

    public TestContext (Class classUnderTest) {
        this(classUnderTest, null, ILLEGAL_VALIDATION_METHOD, testTargetName, mandatoryTestGenerator)
    }

    public String getTestedClassName() {
        return classUnderTest.getName()
    }

    public String getTestedClassSimpleName() {
        return classUnderTest.getSimpleName()
    }

    String getMethodUnderTest() {
        return methodUnderTest
    }

    String getTestTargetName() {
        return testTargetName ? testTargetName : methodUnderTest
    }

    MandatoryTestGeneratorForMethod getMandatoryTestGenerator() {
        return mandatoryTestGenerator
    }

    public Collection<String> applyContextValidationTo(Object... valuesToTest) {
        return validationMethod(valuesToTest)
    }

    TestContext withValidationMethod(Closure validationMethod) {
        return new TestContext(classUnderTest, methodUnderTest, validationMethod, testTargetName)
    }

    TestContext withMethodUnderTest(String methodUnderTest) {
        return new TestContext(classUnderTest, methodUnderTest, validationMethod, testTargetName)
    }

    TestContext withAliasForTestTarget(String aliasForTestTarget) {
        return new TestContext(classUnderTest, methodUnderTest, validationMethod, aliasForTestTarget)
    }

    TestContext withMandatoryTestGenerator(MandatoryTestGeneratorForMethod mandatoryTestGenerator) {
        return new TestContext(classUnderTest, methodUnderTest, validationMethod, testTargetName, mandatoryTestGenerator)
    }
}
