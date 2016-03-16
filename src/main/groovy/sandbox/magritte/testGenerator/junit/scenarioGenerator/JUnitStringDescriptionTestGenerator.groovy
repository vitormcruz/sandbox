package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.testGenerator.MandatoryTestGeneratorForMethod
import sandbox.magritte.testGenerator.TestContext

class JUnitStringDescriptionTestGenerator extends JunitObjectDescriptionTestGenerator implements StringDescription, ScenarioGenerator{

    def getScenariosStrategy = {throw new UnsupportedOperationException()}

    JUnitStringDescriptionTestGenerator(TestContext testContext) {
        super.testContext = testContext
    }

    @Override
    ObjectDescription accessor(String accessor) {
        super.accessor(accessor)
        def setter = "set" + StringUtils.capitalize(accessor)
        testContext = testContext.withMethodUnderTest(setter)
        setMandatoryTestGenerator(MandatoryTestGeneratorForMethod.smartNewFor(JUnitStringDescriptionTestGenerator))
        generators.add({stringTestGenerator ->
            stringTestGenerator.getMandatoryTestGenerator().setTestContext(stringTestGenerator.getTestContext())
            stringTestGenerator.getMandatoryTestGenerator()
        })

        return this
    }
    
    @Override
    StringDescription beNotBlank() {
        return this
    }

    @Override
    StringDescription maxSize(Integer maxSize) {
        generators.add(new StringMaxSizeTestGenerator(maxSize))
        return this
    }

    @Override
    ObjectDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    Collection<Scenario> getScenarios() {
        return getScenariosStrategy()
    }
}
