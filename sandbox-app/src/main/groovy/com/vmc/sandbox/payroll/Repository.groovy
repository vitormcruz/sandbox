package com.vmc.sandbox.payroll

interface Repository<E extends Entity> extends Collection<E>{

    E get(id)

    void update(E employee)
}