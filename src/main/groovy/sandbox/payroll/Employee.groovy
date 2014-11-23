package sandbox.payroll

import sandbox.magritte.Description
import sandbox.magritte.DescriptionMethod
import sandbox.magritte.StringDescription

class Employee {

    def String name

    @DescriptionMethod
    public Description myDescription(){
        return new StringDescription().acessor("name").label("employee.name")
    }

}
