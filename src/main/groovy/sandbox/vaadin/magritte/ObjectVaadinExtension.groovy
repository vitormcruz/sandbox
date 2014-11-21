package sandbox.vaadin.magritte
import com.vaadin.ui.Component
import magritte.StringDescription

class ObjectVaadinExtension {

    public static Component asVaadinComponent(Object anObject){
        StringDescription description = new StringDescription().acessor("name").label("employee.name")
        return description.asVaadinComponentFor(anObject)
//        	^ MAPragmaBuilder for: self
    }

}
