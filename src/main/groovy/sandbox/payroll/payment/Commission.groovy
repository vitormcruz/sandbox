package sandbox.payroll.payment

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
    void postPaymentInfo(PaymentAttachment paymentInfo) {
        if(!paymentInfo instanceof SalesReceipt){
            notifier.issueError("employee.payment.commission.sales.receipt.payment.info.only")
        }

        paymentAttachments.add(paymentInfo)
    }
}
