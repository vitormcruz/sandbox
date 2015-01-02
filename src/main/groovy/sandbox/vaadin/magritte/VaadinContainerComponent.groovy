package sandbox.vaadin.magritte

import com.vaadin.ui.FormLayout
import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer

//TODO it would be better to have something more generic than a form layout.
class VaadinContainerComponent extends FormLayout implements DescriptionContainer{

    private def descriptedObject

    VaadinContainerComponent(descriptedObject) {
        this.descriptedObject = descriptedObject
    }

    @Override
    DescriptionContainer addAll(Collection<? extends Description> collection) {
        collection.each {
            this.addComponent(it.asVaadinComponentFor(descriptedObject))
        }
        return this
    }
}
