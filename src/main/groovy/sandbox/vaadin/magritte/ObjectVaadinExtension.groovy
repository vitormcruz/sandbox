package sandbox.vaadin.magritte
import com.vaadin.ui.Component
import sandbox.magritte.MagritteDescriptionFactory

class ObjectVaadinExtension {

    public static Component asVaadinComponent(Object anObject){
        def description = MagritteDescriptionFactory.forObject(anObject)
        return description.asVaadinComponentFor(anObject)

    }

}
