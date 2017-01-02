package com.vmc.sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSalesReceipt is a Querydsl query type for SalesReceipt
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSalesReceipt extends EntityPathBase<SalesReceipt> {

    private static final long serialVersionUID = -1763985026L;

    public static final QSalesReceipt salesReceipt = new QSalesReceipt("salesReceipt");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QSalesReceipt(String variable) {
        super(SalesReceipt.class, forVariable(variable));
    }

    public QSalesReceipt(Path<? extends SalesReceipt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalesReceipt(PathMetadata metadata) {
        super(SalesReceipt.class, metadata);
    }

}

