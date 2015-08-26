package sandbox.magritte.description.recordingDescription

import static com.google.common.base.Preconditions.checkArgument

/**
 * I am a implementation of the description model that behave like all description interfaces. I mimic the interface
 * provided and then record each an every method call made to myself, After that I can be played back to any object that
 * can respond to the interface I was recorded, so that a new specific model can be build upon the same calls that made
 * the generic description model.
 *
 * @param <T> the type being recorded
 */
class MessageRecorder<T> {

    private Class<T> interfaceBeenRecorded
    def final List<RecordedMessage> recordedMethods = []

    //TODO use validation framework? To do this, I must provide a way for the framework to fast fail with the actual exception instead of ValidationError
    MessageRecorder(Class<T> interfaceToRecord) {
        checkArgument(interfaceToRecord != null, "No interface to record was specified")
        checkArgument(interfaceToRecord.isInterface(), "You specified the class ${interfaceToRecord.getSimpleName()}, " +
                                                           "but I can only record interfaces")
        this.interfaceBeenRecorded = interfaceToRecord
    }


    def T methodMissing(String nameOfMethodToRecord, args) {
        recordedMethods.add(new RecordedMessage(nameOfMethodToRecord, args, interfaceBeenRecorded))
        return asTypeBeingRecorded()
    }

    def T asTypeBeingRecorded() {
        return this.asType(interfaceBeenRecorded)
    }

    Class<T> getInterfaceBeenRecorded() {
        return interfaceBeenRecorded
    }

    def playbackAt(aDescriptorVisitor){
        recordedMethods.each { it.sendMyselfTo(aDescriptorVisitor) }
        return aDescriptorVisitor
    }
}
