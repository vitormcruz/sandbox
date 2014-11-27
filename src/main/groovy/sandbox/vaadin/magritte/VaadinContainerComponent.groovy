package sandbox.vaadin.magritte

import com.vaadin.ui.FormLayout
import sandbox.magritte.Description
import sandbox.magritte.IDescriptionContainer

//TODO it would be better to have something more generic than a form layout.
class VaadinContainerComponent extends FormLayout implements IDescriptionContainer{

    private def descriptedObject

    VaadinContainerComponent(descriptedObject) {
        this.descriptedObject = descriptedObject
    }

    @Override
    IDescriptionContainer addAll(Collection<? extends Description> collection) {
        collection.each {
            this.addComponent(it.asVaadinComponentFor(descriptedObject))
        }
        return this
    }

    @Override
    Description acessor(String acessor) {
        return null
    }

    @Override
    Description beRequired() {
        return null
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return null
    }

    @Override
    Description label(Object label) {
        return null
    }
}
