package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.BaseDescription
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.testGenerator.MandatoryTestGenerator

abstract class JunitTestGeneratorForBaseDescription implements BaseDescription, MethodGenerator {

    //TODO this is used here and in MandatoryTestGenerator. There should be a higher level class with this and with the getter
    def protected testScenarios = []
    def protected accessor
    def protected Class describedClass
    protected MandatoryTestGenerator mandatoryTestGenerator

    @Override
    Description accessor(String accessor) {
        this.accessor = accessor
        return this
    }

    @Override
    Description beRequired() {
        mandatoryTestGenerator.requiredAccessor(accessor)
        return this
    }

    @Override
    public Collection<GeneratedMethod> getGeneratedMethods() {
        return testScenarios
    }

    //TODO extract into an interface
    void setMandatoryTestGenerator(MandatoryTestGenerator mandatoryTestGenerator) {
        this.mandatoryTestGenerator = mandatoryTestGenerator
    }
}
