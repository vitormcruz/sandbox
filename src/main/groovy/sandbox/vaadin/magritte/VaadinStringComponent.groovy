package sandbox.vaadin.magritte

import com.vaadin.ui.TextField
import magritte.AbstractVisitableDescription
import magritte.Description
import magritte.MagnitudeDescription

class VaadinStringComponent extends TextField implements MagnitudeDescription {

    private def sourceObject

    VaadinStringComponent(sourceObject) {
        this.sourceObject = sourceObject
    }

    @Override
    AbstractVisitableDescription acessor(String acessor) {
        this.setValue(sourceObject."$acessor")
    }

    @Override
    AbstractVisitableDescription beRequired() {
        return this
    }

    @Override
    AbstractVisitableDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    Description label(label) {
        this.setCaption(label)
        return this
    }
}
