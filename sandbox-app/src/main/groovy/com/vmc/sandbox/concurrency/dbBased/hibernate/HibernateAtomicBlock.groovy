package com.vmc.sandbox.concurrency.dbBased.hibernate

import org.springframework.transaction.support.TransactionTemplate
import com.vmc.sandbox.concurrency.AtomicBlock

class HibernateAtomicBlock implements AtomicBlock{

    private TransactionTemplate transactionTemplate = TransactionTemplate.smartNewFor(HibernateAtomicBlock)

    @Override
    void execute(Closure unitOfWork) {
        transactionTemplate.execute{
                unitOfWork()
        }
    }

}
