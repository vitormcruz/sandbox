package com.vmc.sandbox.concurrency.inMemory

import com.vmc.sandbox.concurrency.AtomicBlock
import com.vmc.sandbox.concurrency.ModelSnapshot

class InMemoryPersistentModelSnapshot implements ModelSnapshot{

    private AtomicBlock atomicBlock
    private modelObjects = []

    InMemoryPersistentModelSnapshot(AtomicBlock atomicBlock) {
        this.atomicBlock = atomicBlock
    }

    @Override
    def synchronized void save() {
        atomicBlock.execute{
            modelObjects.each {it.executeAllPending()}
        }
    }

    @Override
    void add(Object modelObject) {
        modelObjects.add(modelObject)
    }
}
