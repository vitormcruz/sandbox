package sandbox.magritte.vaadin.viewGenerator
import com.vaadin.ui.TextField
import sandbox.magritte.description.BaseDescription
import sandbox.magritte.description.StringDescription

class VaadinStringComponent extends TextField implements StringDescription {

    private def sourceObject

    VaadinStringComponent(sourceObject) {
        this.sourceObject = sourceObject
    }

    @Override
    BaseDescription accessor(String accessor) {
        this.setValue(sourceObject."$accessor")
        return this
    }

    //TODO Test and implement all that is below.
    @Override
    BaseDescription beRequired() {
        return this
    }

    @Override
    BaseDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    BaseDescription label(label) {
        this.setCaption(label)
        return this
    }

    @Override
    StringDescription beNotBlank() {
        return this
    }

    StringDescription maxSize(Integer maxSize) {
        return this
    }
}
