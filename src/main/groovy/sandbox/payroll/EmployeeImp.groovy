package sandbox.payroll
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.MandatorySetterValidation
import sandbox.validationNotification.builder.GenericValidationNotifierBasedBuilder

import static sandbox.magritte.description.builder.DescriptionFactory.New

class EmployeeImp implements IEmployee{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private Long id
    def String name
    private MandatorySetterValidation mandatoryNameValidation = new MandatorySetterValidation("employee.name", "payroll.employee.name.mandatory")

    def String address
    private MandatorySetterValidation mandatoryAddressValidation = new MandatorySetterValidation("employee.address", "payroll.employee.address.mandatory")

    def String email
    private MandatorySetterValidation mandatoryEmailValidation = new MandatorySetterValidation("employee.email", "payroll.employee.email.mandatory")

    def PaymentMethod paymentMethod
    private MandatorySetterValidation mandatoryPaymentMethodValidation = new MandatorySetterValidation("employee.paymentMethod", "payroll.employee.paymentMethod.mandatory")

    protected EmployeeImp() {
        notifier.issueMandatoryObligation("employee.name", "payroll.employee.name.mandatory")
        notifier.issueMandatoryObligation("employee.address", "payroll.employee.address.mandatory")
        notifier.issueMandatoryObligation("employee.email", "payroll.employee.email.mandatory")
        notifier.issueMandatoryObligation("employee.paymentMethod", "payroll.employee.paymentMethod.mandatory")
    }

    @Override
    Long getId() {
        return id
    }

    @Override
    void setName(String name) {
        if(mandatoryNameValidation.canSet(name)){
            this.name = name
        }
    }

    @Override
    void setAddress(String address) {
        if(mandatoryAddressValidation.canSet(address)){
            this.address = address
        }
    }

    @Override
    void setEmail(String email) {
        if(mandatoryEmailValidation.canSet(email)){
            this.email = email
        }
    }

    @Override
    void setPaymentMethod(PaymentMethod paymentMethod) {
        if(mandatoryPaymentMethodValidation.canSet(paymentMethod)){
            this.paymentMethod = paymentMethod
        }
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

    public static class EmployeeBuilder extends GenericValidationNotifierBasedBuilder implements IEmployee{

        @Delegate
        private EmployeeImp builtEmployee = new EmployeeImp()

        EmployeeBuilder() {
            builderObserver.setBuiltEntity(builtEmployee)
        }
    }
}
