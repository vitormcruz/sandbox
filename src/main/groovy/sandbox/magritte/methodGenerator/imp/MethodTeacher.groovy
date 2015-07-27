package sandbox.magritte.methodGenerator.imp

import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator

/**
 * <pre>
 *
 *
 * Example:
 *
 * </pre>
 */
class MethodTeacher {

    MethodTeacher() {
    }

    def teach(someObject, Collection<GeneratedMethod> methodsToGenerate) {
        validateMethodsToGenerate(methodsToGenerate)
        Collection<MetaMethod> methodsCreated = []
        methodsToGenerate.each {
            it.teachMyselfTo(someObject)
            methodsCreated.add(someObject.class.metaClass.getMetaMethod(it.methodName))
        }
        return methodsCreated
    }

    //TODO framework validation
    private void validateMethodsToGenerate(Collection<MethodGenerator> methodsToGenerate) {
        if (methodsToGenerate == null) {
            throw new IllegalArgumentException("You should provide a non null collection of methods to teach")
        }
    }

}
