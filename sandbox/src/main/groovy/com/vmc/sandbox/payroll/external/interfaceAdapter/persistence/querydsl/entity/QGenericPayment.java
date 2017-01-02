package com.vmc.sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.vmc.sandbox.payroll.payment.type.GenericPaymentType;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QGenericPayment is a Querydsl query type for GenericPayment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGenericPayment extends EntityPathBase<GenericPaymentType> {

    private static final long serialVersionUID = 485333409L;

    public static final QGenericPayment genericPayment = new QGenericPayment("genericPayment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QGenericPayment(String variable) {
        super(GenericPaymentType.class, forVariable(variable));
    }

    public QGenericPayment(Path<? extends GenericPaymentType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenericPayment(PathMetadata metadata) {
        super(GenericPaymentType.class, metadata);
    }

}

