package com.vmc.sandbox.concurrency.inMemory

import com.vmc.sandbox.concurrency.AtomicBlock

class InMemoryAtomicBlock implements AtomicBlock{

    @Override
    public synchronized void execute(Closure unitOfWork) {
        unitOfWork()
    }

}
