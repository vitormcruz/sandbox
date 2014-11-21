package sandbox
import com.vaadin.ui.AbsoluteLayout
import com.vaadin.ui.FormLayout

class PayrollPage extends AbsoluteLayout {

    PayrollPage() {
        def form = new FormLayout()
        this.addComponent(form)
        def employee = new Employee()
        employee.name = "Vitor"
        form.addComponent(employee.asVaadinComponent())
    }
}
