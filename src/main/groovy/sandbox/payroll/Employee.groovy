package sandbox.payroll

import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.DescriptionMethod
import sandbox.magritte.description.StringDescription
import sandbox.validator.Validation
import sandbox.validator.ValidatorTrait
import static sandbox.magritte.description.DescriptionFactory.New
import static sandbox.magritte.description.DescriptionFactory.newContainer

class Employee implements ValidatorTrait {

    def String name
    def String address
    def String email

    //TODO how to use only Interfaces? Such as IDescriptionContainter.new().acessor...? Or maby a global variable (coud be configured by spring, but I want to avoid that)
    @DescriptionMethod
    public myDescription(){
        return newContainer(DescriptionContainer, New(StringDescription).acessor("name").label("employee.name"),
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
