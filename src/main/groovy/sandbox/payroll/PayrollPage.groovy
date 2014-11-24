package sandbox.payroll
import com.vaadin.ui.AbsoluteLayout

class PayrollPage extends AbsoluteLayout {

    PayrollPage() {
        def employee = new Employee(name: "Vitor", address: "rua 1", email: "bla@bla.com")
        this.addComponent(employee.asVaadinComponent())
    }
}
