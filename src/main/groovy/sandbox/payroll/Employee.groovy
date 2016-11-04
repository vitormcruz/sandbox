package sandbox.payroll

interface Employee {

    Long getId()

    String getName()

    void setName(String name)

    String getAddress()

    void setAddress(String address)

    String getEmail()

    void setEmail(String email)

    PaymentData getPaymentData()

    void setPaymentData(PaymentData payment)
}