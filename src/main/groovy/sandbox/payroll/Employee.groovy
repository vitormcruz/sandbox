package sandbox.payroll
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.validator.ValidatorTrait

class Employee implements ValidatorTrait {

    def Long id
    def String name
    def String address
    def String email

    @DescriptionModelDefinition
    public myDescription(){
        return ["name".isAString().maxSize(50).label("employee.name").beRequired(),
                "address".isAString().maxSize(200).label("employee.address").beRequired(),
                "email".isAString().maxSize(100).label("employee.email").beRequired()]
    }
}
