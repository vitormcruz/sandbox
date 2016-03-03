package sandbox.magritte.testGenerator.junit.scenarioGenerator

import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.GeneratedMethod

class JUnitTestsGeneratorForStringDescription extends JunitTestGeneratorForObjectDescription implements StringDescription{
    private StringMaxSizeTestGenerator maxSizeTestGenerator

    JUnitTestsGeneratorForStringDescription(Class describedClass) {
        super.describedClass = describedClass
    }

    @Override
    ObjectDescription accessor(String accessor) {
        super.accessor = accessor
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
        return super.getGeneratedMethods() + maxSizeTestGenerator.getGeneratedMethods()
    }
}
