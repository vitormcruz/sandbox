package sandbox.magritte.testGenerator.junit

import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGenerator.GeneratedMethod

class TestGenerator {

    def Collection<GeneratedMethod> getGeneratedMethodsFor(testObject) {
        return MagritteDescriptionModelBuilder.myInstance.forObject(testObject).asTestGenerator().getGeneratedMethods()
    }
}
