package sandbox.magritte.validationGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

class ValidationGeneratorForDescriptionContainer implements DescriptionContainer, MethodGenerator {

    Collection<SimpleGeneratedMethod> validatons = []

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return validatons
    }

    @Override
    DescriptionContainer addAll(Description... descriptions) {
        descriptions.each {
            validatons.addAll(it.asMethodGenerator().getGeneratedMethods())
        }
        return this
    }
}
