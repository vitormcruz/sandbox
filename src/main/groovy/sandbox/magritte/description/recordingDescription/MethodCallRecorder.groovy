package sandbox.magritte.description.recordingDescription
/**
 *
 * @param <T> Type beeing recorded
 */
class MethodCallRecorder<T> {

    public static final int METHOD_NAME = 0
    public static final int METHOD_ARGUMENTS = 1


    /**
     * A list of method calls. Each method call is represented by a list with a name and a list of arguments.
     */
    def final List messagesSend = []
    private Class<T> clazz

    MethodCallRecorder(Class<T> clazz) {
        //TODo change for validation framework
        if(clazz == null) throw new IllegalArgumentException("Cannot be created without specifying a class.")
        if(!clazz.isInterface()) throw new IllegalArgumentException("I can be created for interfaces only.")
        this.clazz = clazz
    }


    def T methodMissing(String methodName, args) {
        def Object methodFound = clazz.methods.find { it.name == methodName }

        //TODO use validation framework here
        validateMethodFound(methodFound, methodName, args)

        messagesSend.add([methodName, args])
        return asTypeBeeingRecorded()
    }

    private void validateMethodFound(methodFound, methodName, args) {
        if (methodFound == null) {
            throw new MissingMethodException(methodName, clazz, args)
        }
    }

    def T asTypeBeeingRecorded() {
        return this.asType(clazz)
    }

    def accept(aDescriptorVisitor){
        messagesSend.each {
            aDescriptorVisitor."${it[METHOD_NAME]}"(*it[METHOD_ARGUMENTS])
        }

        return aDescriptorVisitor
    }
}
