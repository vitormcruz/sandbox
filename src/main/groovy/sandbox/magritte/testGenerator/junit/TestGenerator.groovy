package sandbox.magritte.testGenerator.junit

import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod

class TestGenerator {

    private MagritteDescriptionModelBuilder modelBuilder = MagritteDescriptionModelBuilder.smartNewFor(JUnit4TestGeneratorRunner);

    def Collection<GeneratedMethod> getGeneratedMethodsFor(testObject) {
        return modelBuilder.forObject(testObject).asTestGenerator().getGeneratedMethods()
    }
}
