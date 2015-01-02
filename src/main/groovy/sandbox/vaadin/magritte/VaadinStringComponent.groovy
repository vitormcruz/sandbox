package sandbox.vaadin.magritte
import com.vaadin.ui.TextField
import sandbox.magritte.Description
import sandbox.magritte.StringDescription

class VaadinStringComponent extends TextField implements StringDescription {

    private def sourceObject

    VaadinStringComponent(sourceObject) {
        this.sourceObject = sourceObject
    }

    @Override
    Description acessor(String acessor) {
        this.setValue(sourceObject."$acessor")
        return this
    }

    //TODO Test and implement all that is below.
    @Override
    Description beRequired() {
        return this
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return this
    }

    @Override
    Description label(label) {
        this.setCaption(label)
        return this
    }

    StringDescription maxSize(Integer maxSize) {
        return this
    }
}
