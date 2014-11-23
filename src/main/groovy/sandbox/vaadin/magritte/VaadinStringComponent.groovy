package sandbox.vaadin.magritte
import com.vaadin.ui.TextField
import sandbox.magritte.Description
import sandbox.magritte.MagnitudeDescription

class VaadinStringComponent extends TextField implements MagnitudeDescription {

    private def sourceObject

    VaadinStringComponent(sourceObject) {
        this.sourceObject = sourceObject
    }

    @Override
    Description acessor(String acessor) {
        this.setValue(sourceObject."$acessor")
        return this
    }

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
}
