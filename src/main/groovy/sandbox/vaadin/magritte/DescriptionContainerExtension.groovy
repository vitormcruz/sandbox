package sandbox.vaadin.magritte

import com.vaadin.ui.Component
import com.vaadin.ui.FormLayout
import sandbox.magritte.DescriptionContainer

class DescriptionContainerExtension {

    public static Component asVaadinComponentFor(DescriptionContainer aDescriptionContainer, descriptedObject){
        //TODO it would be better to have something more generic than a form layout.
        def formLayout = new FormLayout()
        aDescriptionContainer.each {
            formLayout.addComponent(it.asVaadinComponentFor(descriptedObject))
        }
        return formLayout
    }

}
