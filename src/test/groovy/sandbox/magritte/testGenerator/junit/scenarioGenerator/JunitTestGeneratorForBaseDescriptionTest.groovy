package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.junit.Ignore
import org.junit.Test
import sandbox.magritte.description.Description

class JunitTestGeneratorForBaseDescriptionTest{

    @Test
    @Ignore
    def void "An error occurs if the accessor do not exists"(){}

    @Test
    @Ignore
    def void "Generate no tests for no required field"(){
        assert new JunitTestGeneratorForBaseDescriptionPartial().accessor("test")
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

    class JunitTestGeneratorForBaseDescriptionPartial extends JunitTestGeneratorForBaseDescription{

        @Override
        Description accessor(String accessor) {
            return null
        }

        @Override
        Description defaultValue(Object defaultValue) {
            return null
        }

        @Override
        Description label(Object label) {
            return null
        }

    }

}
