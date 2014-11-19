package sandbox
import com.vaadin.ui.AbsoluteLayout
import com.vaadin.ui.MenuBar

class PayrollPage extends AbsoluteLayout {


    PayrollPage() {
        def bar = new MenuBar()
        bar.addItem("Teste", null)
        this.addComponent(bar)
    }
}
