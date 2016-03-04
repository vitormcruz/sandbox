package sandbox.magritte.testGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.description.TestDescription

class TestGeneratorForTestDescription implements TestDescription, MethodGenerator {

    Collection<GeneratedMethod> generatedMethods
    private Closure validationMethod

    @Override
    def TestGeneratorForTestDescription descriptionsFor(Class classUnderTest, Description... descriptions) {
        //TODO use validation framework
        validate(classUnderTest)
        generatedMethods = generateMethodsFor(classUnderTest, descriptions)
        return this
    }

    @Override
    TestDescription usingThisValidationMethod(Closure validationMethod) {
        this.validationMethod = validationMethod
        return this
    }

    private void validate(Class classUnderTest) {
        if (classUnderTest == null) {
            throw new IllegalArgumentException("Cannot create test scenarios for a TestDescription that do not specify a target class")
        }
    }

    private List<GeneratedMethod> generateMethodsFor(classUnderTest, Description... descriptions) {
        descriptions.collectMany {
            it.asTestGenerator(classUnderTest).getGeneratedMethods()
        }
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return generatedMethods
    }
}
