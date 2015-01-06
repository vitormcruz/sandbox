package sandbox.magritte.vaadin.viewGenerator

import com.vaadin.ui.FormLayout
import sandbox.magritte.description.Description
import sandbox.magritte.description.DescriptionContainer

//TODO it would be better to have something more generic than a form layout.
class VaadinContainerComponent extends FormLayout implements DescriptionContainer{

    private def descriptedObject

    VaadinContainerComponent(descriptedObject) {
        this.descriptedObject = descriptedObject
    }

    @Override
    DescriptionContainer addAll(Description ...collection) {
        collection.each {
            this.addComponent(it.asVaadinComponentFor(descriptedObject))
        }
        return this
    }
}
