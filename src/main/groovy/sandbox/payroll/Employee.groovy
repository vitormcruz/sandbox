package sandbox.payroll
import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.validator.Validation
import sandbox.validator.ValidatorTrait

import static sandbox.magritte.description.DescriptionFactory.New

class Employee implements ValidatorTrait {

    def String name
    def String address
    def String email

    @DescriptionModelDefinition
    public myDescription(){
        return New(DescriptionContainer).addAll(New(StringDescription).acessor("name").label("employee.name"),
                                                New(StringDescription).acessor("address").label("employee.address"),
                                                New(StringDescription).acessor("email").label("employee.email"))
    }

    @Validation
    public void validateNameMaxLength(){
        //TODO change for validation api based on description
        if(name.length() > 100) {
            throw new IllegalArgumentException("employee.validation.name.maxsize.error")
        }
    }

}
