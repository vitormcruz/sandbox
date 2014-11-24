package sandbox.payroll

import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
import sandbox.magritte.DescriptionMethod
import sandbox.magritte.StringDescription

class Employee {

    def String name
    def String address
    def String email

    @DescriptionMethod
    public Description myDescription(){
        return new DescriptionContainer(
                    new StringDescription().acessor("name").label("employee.name"),
                    new StringDescription().acessor("address").label("employee.address"),
                    new StringDescription().acessor("email").label("employee.email"))
    }

}
