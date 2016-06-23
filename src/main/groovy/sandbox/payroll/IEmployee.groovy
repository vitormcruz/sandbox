package sandbox.payroll

interface IEmployee {

    Long getId()

    void setName(String name)

    void setAddress(String address)

    void setEmail(String email)

    void setPaymentMethod(PaymentMethod paymentMethod)
}