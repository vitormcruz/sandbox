package com.vmc.sandbox.concurrency.inMemory

import com.vmc.sandbox.concurrency.AtomicBlock
import com.vmc.sandbox.concurrency.ModelSnapshot

class InMemoryPersistentModelSnapshot implements ModelSnapshot{

    private AtomicBlock atomicBlock = AtomicBlock.smartNewFor(InMemoryPersistentModelSnapshot)
    private modelObjects = []

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
