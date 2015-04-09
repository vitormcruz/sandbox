package sandbox.magritte.methodGenerator.imp

import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator

//TODO Make teacher teach only if not already taught
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

    def teach(Class aClass, Collection<GeneratedMethod> methodsToGenerate) {
        validateMethodsToGenerate(methodsToGenerate)
        Collection<MetaMethod> methodsCreated = []
        methodsToGenerate.each {
            aClass.metaClass."${it.methodName}" << it.getClosure()
            methodsCreated.add(aClass.metaClass.getMetaMethod(it.methodName))
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
