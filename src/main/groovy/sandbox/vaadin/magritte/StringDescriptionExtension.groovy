package sandbox.vaadin.magritte

import com.vaadin.ui.Component
import sandbox.magritte.StringDescription

class StringDescriptionExtension {

    public static Component asVaadinComponentFor(StringDescription aStringDescrition, descriptedObject){
        def component = new VaadinStringComponent(descriptedObject)
        aStringDescrition.accept(component)
        return component
    }

}
