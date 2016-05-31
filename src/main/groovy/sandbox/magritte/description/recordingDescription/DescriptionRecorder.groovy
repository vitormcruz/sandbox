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
class DescriptionRecorder<T> {

    private Class<T> interfaceBeenRecorded
    def final List<InterfaceSpecificRecordedMessage> recordedMethods = []

    DescriptionRecorder(Class<T> interfaceToRecord) {
        this.interfaceBeenRecorded = interfaceToRecord
        validateInterfaceBeenRecorded()
    }

    def void validateInterfaceBeenRecorded(){
        checkArgument(interfaceBeenRecorded != null, "No interface to record was specified")
        checkArgument(interfaceBeenRecorded.isInterface(), "You specified the class ${interfaceBeenRecorded.getSimpleName()}, " +
                "but I can only record interfaces")
    }

    def T methodMissing(String nameOfMethodToRecord, args) {
        recordedMethods.add(new InterfaceSpecificRecordedMessage(nameOfMethodToRecord, args, interfaceBeenRecorded))
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