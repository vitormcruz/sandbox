package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.description.ObjectDescription

class JunitTestGeneratorForObjectDescriptionTest {

    @Test
    @Ignore
    def void "An error occurs if the accessor do not exists"(){}

    @Test
    @Ignore
    def void "Generate no tests for no required field"(){
        assert new JunitTestGeneratorForObjectDescriptionPartial().accessor("test")
                                                                .beRequired()
                                                                .getGeneratedMethods().isEmpty()
    }

    @Test
    @Ignore
    def void "Generate one test for one required field"(){}

    @Test
    @Ignore
    def void "Generate three tests for two required field"(){}

    @Test
    @Ignore
    def void "Generate four tests for three required field"(){}

    class JunitTestGeneratorForObjectDescriptionPartial extends JunitTestGeneratorForObjectDescription{

        @Override
        ObjectDescription accessor(String accessor) {
            return null
        }

        @Override
        ObjectDescription defaultValue(Object defaultValue) {
            return null
        }

        @Override
        ObjectDescription label(String label) {
            return null
        }

    }

}
