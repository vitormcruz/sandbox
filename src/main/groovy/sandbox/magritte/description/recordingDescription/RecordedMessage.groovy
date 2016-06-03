package sandbox.magritte.description.recordingDescription

import java.lang.reflect.Method

/**
 * I am a valid recorded message. By valid I mean I assure I can be sent to an object that implements the
 * receiverInterface. If receiverInterface cannot respond to the message then my creation fail with a
 * MissingMethodException.
 */
class RecordedMessage {
    def final name
    def final args

    RecordedMessage(name, args, Class receiverInterface) {
        this.name = name
        this.args = args == null ? [] : args
        validateInterfaceRespondsToMessage(receiverInterface)
    }

    def void validateInterfaceRespondsToMessage(Class receiverInterface){
        def messageToRecord = receiverInterface.methods.find { it.name == name && argumentsMatch(it) }
        if (messageToRecord == null) {
            throw new MissingMethodException(name, receiverInterface, args)
        }
    }

    private boolean argumentsMatch(Method method) {
        if(method.isVarArgs()){
            return method.parameterTypes.length <= args.size()
        }
        return method.parameterTypes.length == args.size()
    }

    def sendMyselfTo(objectOfTypeRecorded){
        objectOfTypeRecorded."${name}"(*args)
    }
}
