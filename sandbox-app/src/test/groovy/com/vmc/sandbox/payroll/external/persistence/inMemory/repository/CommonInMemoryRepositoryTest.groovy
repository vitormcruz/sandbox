package com.vmc.sandbox.payroll.external.persistence.inMemory.repository

import com.vmc.sandbox.payroll.Employee
import org.junit.Test


class CommonInMemoryRepositoryTest {

    @Test
    def void "asas"(){
        def test = new CommonInMemoryRepository()
        test.add(new Employee())
        test.executeAllPending()
    }
}
