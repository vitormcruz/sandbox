package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.testGenerator.MandatoryTestGeneratorForMethod

class JUnitStringDescriptionTestGenerator extends JunitObjectDescriptionTestGenerator implements StringDescription{
    private ValidationFactory validationFactory = new ValidationFactory()
    private StringMaxSizeTestGenerator maxSizeTestGenerator

    JUnitStringDescriptionTestGenerator(Class describedClass) {
        super.describedClass = describedClass
    }

    @Override
    ObjectDescription accessor(String accessor) {
        def setter = "set" + StringUtils.capitalize(accessor)
        def validationMethod = validationFactory.getValidationMethodFor(setter, describedClass)
        setValidationMethod(validationMethod)
        MandatoryTestGeneratorForMethod mandatoryTestGenerator = MandatoryTestGeneratorForMethod.smartNewFor(JUnitStringDescriptionTestGenerator);
        mandatoryTestGenerator.setClassUnderTest(describedClass)
        mandatoryTestGenerator.setMethodUnderTest(setter)
        mandatoryTestGenerator.setValidationMethod(validationMethod)
        setMandatoryTestGenerator(mandatoryTestGenerator)
        super.accessor = accessor
        if (!label) {
            label = accessor
        }
        return this
    }
    
    @Override
    StringDescription beNotBlank() {
        return this
    }

    @Override
    StringDescription maxSize(Integer maxSize) {
        maxSizeTestGenerator = new StringMaxSizeTestGenerator(label, maxSize, describedClass, validationMethod)
        return this
    }

    @Override
    ObjectDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        def mandatoryGeneratedMethods = accessor ? mandatoryTestGenerator.getGeneratedMethods() : []
        return super.getGeneratedMethods() + maxSizeTestGenerator.getGeneratedMethods() + mandatoryGeneratedMethods
    }
}
