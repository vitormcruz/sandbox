package sandbox.magritte.description
import sandbox.magritte.description.recordingDescription.MethodCallRecorder
/**
 * I can be used to create descriptions of many types. I am also a monostate with a default set of description
 * implementations. My state can change once, because I may be used with some set of custom description implementations,
 * but if my implementation change more than once I will complain. I should be used in situations where the same set
 * of description implementations are used across an entire application, if you would like to vary them dependending on
 * the situation, then I am not usefull for you, but that should not be a common situation.
 */
class DescriptionFactory {

    static <T> T New(Class<T> aDescriptionType) {
        def recorder = new MethodCallRecorder(aDescriptionType)
        return recorder.asTypeBeeingRecorded()
    }

    static <T extends DescriptionContainer> T newContainer(Class<T> containerType, ...descriptions){
        def recorder = new MethodCallRecorder(DescriptionContainer).asTypeBeeingRecorded()
        recorder.addAll(descriptions as Collection)
        return recorder
    }
}
