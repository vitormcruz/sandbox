package sandbox

import com.vaadin.ui.Component
import com.vaadin.ui.MenuBar

class EmployeeVaadinExtension {

    public static Component asVaadinComponent(Employee self){
        def bar = new MenuBar()
        bar.addItem("Extension Working", null)
        return bar
    }

}
