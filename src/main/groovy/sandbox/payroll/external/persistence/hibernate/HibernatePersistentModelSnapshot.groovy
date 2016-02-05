package sandbox.payroll.external.persistence.hibernate

import org.springframework.transaction.PlatformTransactionManager
import sandbox.payroll.business.ModelSnapshot
import sandbox.concurrency.AtomicBlock
import sandbox.concurrency.dbBased.hibernate.HibernateAtomicBlock

class HibernatePersistentModelSnapshot implements ModelSnapshot{

    private modelObjects = []
    private AtomicBlock atomicBlock

    HibernatePersistentModelSnapshot(PlatformTransactionManager transactionManager ) {
        atomicBlock = new HibernateAtomicBlock(transactionManager)
    }

    @Override
    def void save() {
        atomicBlock.execute{
            modelObjects.each { it.executeAllPending()}
        }
    }

    @Override
    void add(Object modelObject) {
        modelObjects.add(modelObject)
    }
}
