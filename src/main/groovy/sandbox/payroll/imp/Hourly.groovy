package sandbox.payroll.imp

class Hourly extends GenericPaymentMethod{
    private Integer hourRate

    def Hourly(Integer hourRate) {
        this.hourRate = hourRate
    }

    Integer getHourRate() {
        return hourRate
    }
}
