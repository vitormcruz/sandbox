package com.vmc.sandbox.payroll.external.interfaceAdapter.persistence.hibernate

import com.vmc.sandbox.concurrency.AtomicBlock
import com.vmc.sandbox.concurrency.ModelSnapshot

class HibernatePersistentModelSnapshot implements ModelSnapshot{

    private AtomicBlock atomicBlock = AtomicBlock.smartNewFor(HibernatePersistentModelSnapshot)
    private modelObjects = []

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
