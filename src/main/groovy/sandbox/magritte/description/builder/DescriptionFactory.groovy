package sandbox.magritte.description.builder
import sandbox.magritte.description.recordingDescription.MethodCallRecorder
/**
 * I am kind of a Monostate/Service Locator that can provide for you a default set of descriptions implementationss, that
 * you can change if, for example, for you own optimized set of descriptions implementations. If you, however, change it
 * more than once I will assume some error was made and complain since I represent a global fammily of descriptions and
 * that shouldn't be changing all the time. If, however, you *really* know what you are doing, you can tell me to avoid
 * complaining, but be aware that I was designed to be used as a global in situations where the same set of description
 * implementations are used across an entire application. You should consider carefully doing it diferently since many
 * problems can arise and since it should't really be a common situation.
 */
//TODO Substitute for SmartFactory?
class DescriptionFactory {

    static <T> T New(Class<T> aDescriptionType) {
        def recorder = new MethodCallRecorder(aDescriptionType)
        return recorder.asTypeBeeingRecorded()
    }
}
