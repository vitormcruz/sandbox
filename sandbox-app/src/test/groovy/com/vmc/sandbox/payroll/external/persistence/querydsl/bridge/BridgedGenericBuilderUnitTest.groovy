package com.vmc.sandbox.payroll.external.persistence.querydsl.bridge

import com.vmc.sandbox.validationNotification.builder.GenericBuilderUnitTest
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder


class BridgedGenericBuilderUnitTest extends GenericBuilderUnitTest{

    @Override
    GenericBuilder getBuilderFor(Class<GenericBuilderUnitTest.TestEntity> clazz) {
        return new BridgedGenericBuilder(clazz)
    }
}
