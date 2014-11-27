package sandbox.vaadin.magritte

import com.vaadin.ui.Component
import sandbox.magritte.Description

class DescriptionExtension {
    public static Component asVaadinComponentFor(Description aDescrition, descriptedObject){
        //TODO Return a null component by default. This tell the callee that rhis description is unknow or not treated/ignored by vaadin-magritte implementation. Test this.
    }
}
