package sandbox.payroll.imp

class Commission extends Monthly{

    private Integer commissionRate

    def Commission(Integer salary, Integer commissionRate) {
        super(salary);

        this.commissionRate = commissionRate
    }

    Integer getCommissionRate() {
        return commissionRate
    }
}
