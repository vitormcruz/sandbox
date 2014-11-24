package sandbox.vaadin.magritte

import com.vaadin.ui.Component
import sandbox.magritte.StringDescription

class StringDescriptionExtension {

    public static Component asVaadinComponentFor(StringDescription aStringDescrition, descriptedObject){
        //TODO this will return a component that is not a layout. How to return a layout if it is not part of a container? (start wrint tests)
        def component = new VaadinStringComponent(descriptedObject)
        aStringDescrition.accept(component)
        return component
    }

}
