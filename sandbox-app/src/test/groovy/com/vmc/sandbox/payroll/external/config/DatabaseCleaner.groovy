package com.vmc.sandbox.payroll.external.config

import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.payroll.external.persistence.inMemory.repository.CommonInMemoryRepository

class DatabaseCleaner {
    private ModelSnapshot model = ModelSnapshot.smartNewFor(DatabaseCleaner)

    public void cleanDatabase(){
        CommonInMemoryRepository.smartNewFor(DatabaseCleaner).clear()
        model.save()
    }


}
