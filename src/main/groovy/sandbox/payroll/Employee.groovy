package sandbox.payroll

interface Employee {

    Long getId()

    String getName()

    void setName(String name)

    String getAddress()

    void setAddress(String address)

    String getEmail()

    void setEmail(String email)

    PaymentMethod getPaymentMethod()

    void setPaymentMethod(PaymentMethod paymentMethod)
}