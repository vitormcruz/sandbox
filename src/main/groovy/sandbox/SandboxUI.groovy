package sandbox

import com.vaadin.annotations.Push
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.data.Container.Filter
import com.vaadin.data.Item
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.util.IndexedContainer
import com.vaadin.server.Sizeable
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.*
import com.vaadin.ui.AbstractTextField.TextChangeEventMode
import sandbox.heavyValidation.HeavyValidationPage
import sandbox.payroll.external.vaadin.PayrollPage
/**
 * Copied from Vaadin tutorial to have a working example of it on this project. I will change its contents over time.
 * (https://github.com/vaadin/addressbook/blob/master/src/main/java/com/vaadin/tutorial/addressbook/AddressbookUI.java)
 */
@Title("Sandbox")
@Theme("valo")
@Push
public class SandboxUI extends UI {

    /* User interface components are stored in session. */
    private Table contactList = new Table();
    private TextField searchField = new TextField();
    private Button addNewContactButton = new Button("New");
    private Button removeContactButton = new Button("Remove this contact");
    private FormLayout editorLayout = new FormLayout();
    private FieldGroup editorFields = new FieldGroup();

    private static final String FNAME = "First Name";
    private static final String LNAME = "Last Name";
    private static final String COMPANY = "Company";
    private static final String[] fieldNames = [ FNAME, LNAME,
            COMPANY, "Mobile Phone", "Work Phone", "Home Phone", "Work Email",
            "Home Email", "Street", "City", "Zip", "State", "Country" ] as String[];

    /*
     * Any component can be bound to an external data source. This example uses
     * just a dummy in-memory list, but there are many more practical
     * implementations.
     */
    IndexedContainer contactContainer = createDummyDatasource();

    /*
     * After UI class is created, init() is executed. You should build and wire
     * up your user interface here.
     */
    protected void init(VaadinRequest request) {
//        this.setPollInterval(200)
        initLayout();
        initContactList();
        initEditor();
        initSearch();
        initAddRemoveButtons();
    }

    /*
     * In this example layouts are programmed in Java. You may choose use a
     * visual editor, CSS or HTML templates for layout instead.
     */
    private void initLayout() {
        VerticalSplitPanel baseLayout = new VerticalSplitPanel()
        baseLayout.setLocked(true)
        baseLayout.setSplitPosition(7, Sizeable.Unit.PERCENTAGE)
        setContent(baseLayout)


        /* Root of the user interface component tree is set */
        HorizontalSplitPanel addressBookPanel = new HorizontalSplitPanel();

        def menu = new MenuBar()
        def item = menu.addItem("Experimentations", null)
        item.addItem("AddressBook", { selectedItem -> baseLayout.setSecondComponent(addressBookPanel)} )
        item.addItem("PayRoll", { selectedItem -> baseLayout.setSecondComponent(new PayrollPage())} )
        item.addItem("HeavyValidation", { selectedItem -> baseLayout.setSecondComponent(new HeavyValidationPage())} )
        baseLayout.setFirstComponent(menu)


        /* Build the component tree */
        VerticalLayout leftLayout = new VerticalLayout();
        addressBookPanel.addComponent(leftLayout);
        addressBookPanel.addComponent(editorLayout);
        leftLayout.addComponent(contactList);
        HorizontalLayout bottomLeftLayout = new HorizontalLayout();
        leftLayout.addComponent(bottomLeftLayout);
        bottomLeftLayout.addComponent(searchField);
        bottomLeftLayout.addComponent(addNewContactButton);

        /* Set the contents in the left of the split panel to use all the space */
        leftLayout.setSizeFull();

        /*
         * On the left side, expand the size of the contactList so that it uses
         * all the space left after from bottomLeftLayout
         */
        leftLayout.setExpandRatio(contactList, 1);
        contactList.setSizeFull();

        /*
         * In the bottomLeftLayout, searchField takes all the width there is
         * after adding addNewContactButton. The height of the layout is defined
         * by the tallest component.
         */
        bottomLeftLayout.setWidth("100%");
        searchField.setWidth("100%");
        bottomLeftLayout.setExpandRatio(searchField, 1);

        /* Put a little margin around the fields in the right side editor */
        editorLayout.setMargin(true);
        editorLayout.setVisible(false);
    }

    private void initEditor() {

        editorLayout.addComponent(removeContactButton);

        /* User interface can be created dynamically to reflect underlying data. */
        fieldNames.each { fieldName ->
            TextField field = new TextField(fieldName);
            editorLayout.addComponent(field);
            field.setWidth("100%");

            /*
             * We use a FieldGroup to connect multiple components to a data
             * source at once.
             */
            editorFields.bind(field, fieldName);
        }

        /*
         * Data can be buffered in the user interface. When doing so, commit()
         * writes the changes to the data source. Here we choose to write the
         * changes automatically without calling commit().
         */
        editorFields.setBuffered(false);
    }

    private void initSearch() {

        /*
         * We want to show a subtle prompt in the search field. We could also
         * set a caption that would be shown above the field or description to
         * be shown in a tooltip.
         */
        searchField.setInputPrompt("Search contacts");

        /*
         * Granularity for sending events over the wire can be controlled. By
         * default simple changes like writing a text in TextField are sent to
         * server with the next Ajax call. You can set your component to be
         * immediate to send the changes to server immediately after focus
         * leaves the field. Here we choose to send the text over the wire as
         * soon as user stops writing for a moment.
         */
        searchField.setTextChangeEventMode(TextChangeEventMode.LAZY);

        /*
         * When the event happens, we handle it in the anonymous inner class.
         * You may choose to use separate controllers (in MVC) or presenters (in
         * MVP) instead. In the end, the preferred application architecture is
         * up to you.
         */
        searchField.addTextChangeListener { event ->
            /* Reset the filter for the contactContainer. */
            contactContainer.removeAllContainerFilters();
            contactContainer.addContainerFilter(new ContactFilter(event
                    .getText()));
        }
    }

    /*
     * A custom filter for searching names and companies in the
     * contactContainer.
     */
    private class ContactFilter implements Filter {
        private String needle;

        public ContactFilter(String needle) {
            this.needle = needle.toLowerCase();
        }

        public boolean passesFilter(Object itemId, Item item) {
            String haystack = ("" + item.getItemProperty(FNAME).getValue()
                    + item.getItemProperty(LNAME).getValue() + item
                    .getItemProperty(COMPANY).getValue()).toLowerCase();
            return haystack.contains(needle);
        }

        public boolean appliesToProperty(Object id) {
            return true;
        }
    }

    private void initAddRemoveButtons() {
        addNewContactButton.addClickListener { event ->

            /*
             * Rows in the Container data model are called Item. Here we add
             * a new row in the beginning of the list.
             */
            contactContainer.removeAllContainerFilters();
            Object contactId = contactContainer.addItemAt(0);

            /*
             * Each Item has a set of Properties that hold values. Here we
             * set a couple of those.
             */
            contactList.getContainerProperty(contactId, FNAME).setValue(
                    "New");
            contactList.getContainerProperty(contactId, LNAME).setValue(
                    "Contact");

            /* Lets choose the newly created contact to edit it. */
            contactList.select(contactId);
        }

        removeContactButton.addClickListener { event ->
            Object contactId = contactList.getValue();
            contactList.removeItem(contactId);
        }
    }

    private void initContactList() {
        contactList.setContainerDataSource(contactContainer);
        contactList.setVisibleColumns([FNAME, LNAME, COMPANY ] as String[]);
        contactList.setSelectable(true);
        contactList.setImmediate(true);

        contactList.addValueChangeListener { event ->
            Object contactId = contactList.getValue();

            /*
             * When a contact is selected from the list, we want to show
             * that in our editor on the right. This is nicely done by the
             * FieldGroup that binds all the fields to the corresponding
             * Properties in our contact at once.
             */
            if (contactId != null)
                editorFields.setItemDataSource(contactList
                        .getItem(contactId));

            editorLayout.setVisible(contactId != null);
        }
    }

    /*
     * Generate some in-memory example data to play with. In a real application
     * we could be using SQLContainer, JPAContainer or some other to persist the
     * data.
     */
    private static IndexedContainer createDummyDatasource() {
        IndexedContainer ic = new IndexedContainer();

        fieldNames.each { String p ->
            ic.addContainerProperty(p, String.class, "");
        }

        /* Create dummy data by randomly combining first and last names */
        String[] fnames = ["Peter", "Alice", "Joshua", "Mike", "Olivia",
                "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
                "Lisa", "Marge" ] as String[];
        String[] lnames = [ "Smith", "Gordon", "Simpson", "Brown", "Clavel",
                "Simons", "Verne", "Scott", "Allison", "Gates", "Rowling",
                "Barks", "Ross", "Schneider", "Tate" ] as String[];

        (1..1000).each {
            Object id = ic.addItem();
            ic.getContainerProperty(id, FNAME).setValue(
                    fnames[(int) (fnames.length * Math.random())]);
            ic.getContainerProperty(id, LNAME).setValue(
                    lnames[(int) (lnames.length * Math.random())]);
        }

        return ic;
    }

}
