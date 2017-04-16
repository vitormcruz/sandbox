package com.vmc.sandbox.payroll.external.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt;
import com.vmc.sandbox.payroll.payment.type.Commission;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCommission is a Querydsl query type for Commission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommission extends EntityPathBase<Commission> {

    private static final long serialVersionUID = -1780134051L;

    public static final QCommission commission = new QCommission("commission");

    public final QMonthly _super = new QMonthly(this);

    public final NumberPath<Integer> commissionRate = createNumber("commissionRate", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final SetPath<SalesReceipt, QSalesReceipt> paymentInfos = this.<SalesReceipt, QSalesReceipt>createSet("paymentAttachments", SalesReceipt.class, QSalesReceipt.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Integer> salary = _super.salary;

    public QCommission(String variable) {
        super(Commission.class, forVariable(variable));
    }

    public QCommission(Path<? extends Commission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommission(PathMetadata metadata) {
        super(Commission.class, metadata);
    }

}

