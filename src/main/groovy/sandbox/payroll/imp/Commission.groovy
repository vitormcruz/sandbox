package sandbox.payroll.imp

import sandbox.payroll.PaymentInfo

class Commission extends Monthly{

    private Integer commissionRate

    protected Commission() {
    }

    def Commission(Integer salary, Integer commissionRate) {
        super(salary);

        this.commissionRate = commissionRate
    }

    Integer getCommissionRate() {
        return commissionRate
    }

    @Override
    void postPaymentInfo(PaymentInfo paymentInfo) {
        if(!paymentInfo instanceof SalesReceipt){
            notifier.issueError("employee.payment.commission.sales.receipt.payment.info.only")
        }

        paymentInfos.add(paymentInfo)
    }
}
