package sandbox.payroll;

import sandbox.magritte.description.DescriptionModelDefinition;

public class EmployeeJava {

    public EmployeeJava() {
    }

    @DescriptionModelDefinition
    public Object myDescription(){
//        return New(DescriptionContainer.class).addAll(New(StringDescription.class).accessor("name").maxSize(50).label("employee.name"),
//                                                New(StringDescription.class).accessor("address").maxSize(100).label("employee.address"),
//                                                New(StringDescription.class).accessor("email").label("employee.email"));
        return "";
    }
}
