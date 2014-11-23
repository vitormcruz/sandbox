package sandbox.vaadin.magritte

import com.vaadin.ui.Component
import sandbox.magritte.MagritteDescriptionFactory
import sandbox.magritte.StringDescription

class ObjectVaadinExtension {

    public static Component asVaadinComponent(Object anObject){
        //TODO tinha que retornar uma descrição apenas, criar um container e fazer recursivo.
        StringDescription description = MagritteDescriptionFactory.forObject(anObject)
        return description.asVaadinComponentFor(anObject)

    }

}
