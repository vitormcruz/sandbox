package sandbox.payroll.external.interfaceAdapter.vaadin
import com.vaadin.ui.AbsoluteLayout
import com.vaadin.ui.Button
import sandbox.payroll.Employee

class PayrollPage extends AbsoluteLayout {

    PayrollPage() {
        def employee = new Employee(name: "Vitor", address: "rua 1", email: "bla@bla.com")
        this.addComponent(employee.asVaadinComponent())
        def button = new Button("Save")
        button.addClickListener({employee.teste()})
        this.addComponent(button)
    }
}