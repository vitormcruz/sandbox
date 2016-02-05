package sandbox.concurrency.dbBased.hibernate

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.TransactionCallback
import org.springframework.transaction.support.TransactionTemplate
import sandbox.concurrency.AtomicBlock

class HibernateAtomicBlock implements AtomicBlock{

    private TransactionTemplate transactionTemplate

    HibernateAtomicBlock(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager)
    }

    @Override
    void execute(Closure unitOfWork) {
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            Void doInTransaction(TransactionStatus status) {
                unitOfWork()
                return null
            }
        })
    }

}
