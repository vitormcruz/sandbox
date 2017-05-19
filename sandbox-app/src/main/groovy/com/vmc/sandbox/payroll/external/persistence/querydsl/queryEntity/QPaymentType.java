package com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.sql.ColumnMetadata;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.CommissionEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.EmployeeEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.HourlyEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.MonthlyEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.PaymentTypeEntity;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QPaymentType is a Querydsl query type for PaymentTypeEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPaymentType extends com.querydsl.sql.RelationalPathBase<PaymentTypeEntity> {

    private static final long serialVersionUID = 1230573416;

    public static final QPaymentType paymentType = new QPaymentType("PAYMENT_TYPE");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final com.querydsl.sql.PrimaryKey<PaymentTypeEntity> sysPk10134 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<EmployeeEntity> paymentTypeEmployeeFk = createForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<CommissionEntity> _comissionPaymentTypeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<MonthlyEntity> _monthlyPaymentTypeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<HourlyEntity> _hourlyPaymentTypeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public QPaymentType(String variable) {
        super(PaymentTypeEntity.class, forVariable(variable), "PUBLIC", "PAYMENT_TYPE");
        addMetadata();
    }

    public QPaymentType(String variable, String schema, String table) {
        super(PaymentTypeEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPaymentType(String variable, String schema) {
        super(PaymentTypeEntity.class, forVariable(variable), schema, "PAYMENT_TYPE");
        addMetadata();
    }

    public QPaymentType(Path<? extends PaymentTypeEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "PAYMENT_TYPE");
        addMetadata();
    }

    public QPaymentType(PathMetadata metadata) {
        super(PaymentTypeEntity.class, metadata, "PUBLIC", "PAYMENT_TYPE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
    }

}

