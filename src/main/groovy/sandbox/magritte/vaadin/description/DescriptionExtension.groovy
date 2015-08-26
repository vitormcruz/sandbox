package sandbox.magritte.vaadin.description

import com.vaadin.ui.Component
import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.vaadin.viewGenerator.VaadinContainerComponent
import sandbox.magritte.vaadin.viewGenerator.VaadinStringComponent

//TODO this could be a generic extension on the magritte package. Implementations, such as for vaadin and test generator
class DescriptionExtension {
    public static Component asVaadinComponentFor(Description aDescrition, descriptedObject){
        //TODO Return a null component by default. This tell the callee that rhis description is unknow or not treated/ignored by vaadin-magritte implementation. Test this.
    }

    public static Component asVaadinComponentFor(Collection descriptions, descriptedObject){
        def component = new VaadinContainerComponent(descriptions, descriptedObject)
        return component
    }

    public static Component asVaadinComponentFor(StringDescription aStringDescrition, descriptedObject){
        //TODO this will return a component that is not a layout. How to return a layout if it is not part of a container? (start doing this with tests)
        def component = new VaadinStringComponent(descriptedObject)
        aStringDescrition.playbackAt(component)
        return component
    }
}
