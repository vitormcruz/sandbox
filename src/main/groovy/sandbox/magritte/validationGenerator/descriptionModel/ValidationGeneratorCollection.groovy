package sandbox.magritte.validationGenerator.descriptionModel

import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

class ValidationGeneratorCollection implements MethodGenerator {

    Collection<SimpleGeneratedMethod> validatons = []

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return validatons
    }

    ValidationGeneratorCollection(descriptions, describedObject) {
        descriptions.each {
           validatons.addAll(it.asMethodGenerator(describedObject).getGeneratedMethods())
        }
    }

}
