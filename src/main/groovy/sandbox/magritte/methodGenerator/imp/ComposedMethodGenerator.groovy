package sandbox.magritte.methodGenerator.imp

import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator

/**
 *
  */
class ComposedMethodGenerator implements MethodGenerator{

    def methodGenerators = []

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return methodGenerators.collect {it.getGeneratedMethods()}.flatten()
    }

    def addMethodGenerator(MethodGenerator methodGenerator) {
        methodGenerators.add(methodGenerator)
    }
}
