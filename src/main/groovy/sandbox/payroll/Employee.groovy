package sandbox.payroll

import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.validator.imp.DefaultValidatorTrait

import static sandbox.magritte.description.builder.DescriptionFactory.New

class Employee implements DefaultValidatorTrait {

    def Long id
    def String name
    def String address
    def String email

    @DescriptionModelDefinition
    public myDescription(){
        return New(DescriptionContainer).addAll(New(StringDescription).accessor("name").maxSize(100).label("employee.name"),
                                                New(StringDescription).accessor("address").label("employee.address"),
                                                New(StringDescription).accessor("email").label("employee.email"))
    }
}
