package sandbox.magritte.vaadin
import com.vaadin.ui.Component
import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder

class ObjectVaadinExtension {

    public static Component asVaadinComponent(Object anObject){
        def description = MagritteDescriptionModelBuilder.forObject(anObject)
        return description.asVaadinComponentFor(anObject)

    }

}
