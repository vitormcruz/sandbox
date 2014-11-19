package sandbox
import com.vaadin.ui.AbsoluteLayout

class PayrollPage extends AbsoluteLayout {


    PayrollPage() {
        this.addComponent(new Employee().asVaadinComponent())
    }
}
