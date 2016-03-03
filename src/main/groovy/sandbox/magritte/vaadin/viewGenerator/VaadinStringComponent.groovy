package sandbox.magritte.vaadin.viewGenerator
import com.vaadin.ui.TextField
import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.description.StringDescription

class VaadinStringComponent extends TextField implements StringDescription {

    private def sourceObject

    VaadinStringComponent(sourceObject) {
        this.sourceObject = sourceObject
    }

    @Override
    ObjectDescription accessor(String accessor) {
        this.setValue(sourceObject."$accessor")
        return this
    }

    //TODO Test and implement all that is below.
    @Override
    ObjectDescription beRequired() {
        return this
    }

    @Override
    ObjectDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    ObjectDescription label(String label) {
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
