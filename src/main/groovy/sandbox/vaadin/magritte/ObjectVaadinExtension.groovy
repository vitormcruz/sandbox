package sandbox.vaadin.magritte
import com.vaadin.ui.Component
import sandbox.magritte.imp.MagritteDescriptionModelBuilder

class ObjectVaadinExtension {

    public static Component asVaadinComponent(Object anObject){
        def description = MagritteDescriptionModelBuilder.forObject(anObject)
        return description.asVaadinComponentFor(anObject)

    }

}
