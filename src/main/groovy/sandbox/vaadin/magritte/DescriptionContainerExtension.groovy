package sandbox.vaadin.magritte

import com.vaadin.ui.Component
import sandbox.magritte.DescriptionContainer

class DescriptionContainerExtension {

    public static Component asVaadinComponentFor(DescriptionContainer aDescriptionContainer, descriptedObject){
        def component = new VaadinContainerComponent(descriptedObject)
        aDescriptionContainer.accept(component)
        return component
    }

}
