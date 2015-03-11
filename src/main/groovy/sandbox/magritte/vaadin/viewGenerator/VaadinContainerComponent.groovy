package sandbox.magritte.vaadin.viewGenerator

import com.vaadin.ui.FormLayout

//TODO it would be better to have something more generic than a form layout.
class VaadinContainerComponent extends FormLayout{

    private def descriptedObject

    VaadinContainerComponent(descriptions, descriptedObject) {
        this.descriptedObject = descriptedObject

        collection.each {
            this.addComponent(it.asVaadinComponentFor(descriptedObject))
        }
    }
}
