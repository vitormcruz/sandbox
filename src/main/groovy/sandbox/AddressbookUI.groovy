package sandbox
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

@Title("Addressbook")
@Theme("valo")
public class AddressbookUI extends UI {
    protected void init(VaadinRequest request) {
        System.out.println("ahaaaa!!!");
     }
}
