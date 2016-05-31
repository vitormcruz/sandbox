package sandbox.payroll
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.validationNotification.ApplicationValidationNotifier

import static sandbox.magritte.description.builder.DescriptionFactory.New

class Employee {

    private Long id
    def String name
    def String address
    def String email
    def PaymentMethod paymentMethod

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    public static newEmployee(Map<String, Object> mandatoryProperties) {
        newEmployee(mandatoryProperties.get("name"), mandatoryProperties.get("address"), mandatoryProperties.get("email"))
    }

    public static newEmployee(String name, String address, String email, PaymentMethod paymentMethod) {
        def validationSession = notifier.getValidationSession()
        def employee = new Employee()
        employee.setName(name)
        employee.setAddress(address)
        employee.setEmail(email)
        employee.setPaymentMethod(paymentMethod)
        return validationSession.successful() ? employee : null
    }

    Employee() {
    }



    Long getId() {
        return id
    }

    void setName(String name) {
        if(!name){
            notifier.issueError("employee.name", "payroll.employee.name.mandatory")
            return
        }

        this.name = name
    }

    void setAddress(String address) {
        this.address = address
    }

    void setEmail(String email) {
        this.email = email
    }

    void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod
    }

    public ApplicationValidationNotifier.ValidationSession instantiationResult(){
        return instantiationResult
    }

    @DescriptionModelDefinition
    public myDescription(){

        /**
         * "setName".parameters(
         *         FIRST.isAString().required()
         *         SECOND.isAString().required().maxSize(200))
         *
         * "setName".parameters(
         *         FIRST.isAString().required()
         *         SECOND.isAString().required().maxSize(200)
         *
         *
         * setName message has
         *
         */

        return [New(StringDescription).accessor("name").maxSize(50).label("employee.name").beRequired(),
                New(StringDescription).accessor("address").maxSize(200).label("employee.address").beRequired(),
                New(StringDescription).accessor("email").maxSize(100).label("employee.email").beRequired()]
    }
}
