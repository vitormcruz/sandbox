package sandbox.magritte.methodGeneration.generator.imp
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder
import sandbox.magritte.methodGeneration.description.MethodGenerator
import sandbox.magritte.methodGeneration.generator.GeneratedMethod

/**
 * <pre>
 *
 * Teach a class a new method based on a ConceptDescription //TODO change this name.....
 *
 * Example:
 *
 * </pre>
 */
class MethodTeacher {

    private Class<? extends MethodGenerator> methodGeneratorClass

    MethodTeacher(Class<? extends MethodGenerator> methodGeneratorClass) {
        validateMethodGeneratorClass(methodGeneratorClass)
        this.methodGeneratorClass = methodGeneratorClass
    }

    //TODO framework validation
    private void validateMethodGeneratorClass(Class<? extends MethodGenerator> methodGeneratorClass) {
        if (methodGeneratorClass == null) {
            throw new IllegalArgumentException("You should provide a MethodGenerator implementation class")
        }
    }

    def teach(Class aClass) {
        Collection<SimpleGeneratedMethod> generatedMethods = getGeneratedMethodsFor(aClass)
        return teachClassEachMethod(aClass, generatedMethods)
    }

    private Collection<SimpleGeneratedMethod> getGeneratedMethodsFor(Class aClass) {
        def descriptionModel = MagritteDescriptionModelBuilder.forObject(aClass.newInstance())
        def methodGenerator = methodGeneratorClass.newInstance()
        descriptionModel.accept(methodGenerator)
        return methodGenerator.getGeneratedMethods()
    }

    private Collection<MetaMethod> teachClassEachMethod(aClass, Collection<GeneratedMethod> generatedMethods) {
        Collection<MetaMethod> methodsCreated = []
        generatedMethods.each {
            aClass.metaClass."${it.methodName}" << it.getClojure()
            methodsCreated.add(aClass.metaClass.getMetaMethod(it.methodName))
        }

        return methodsCreated
    }
}
