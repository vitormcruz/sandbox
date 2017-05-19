package com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.sql.ColumnMetadata;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.MonthlyEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.PaymentTypeEntity;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QMonthly is a Querydsl query type for MonthlyEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMonthly extends com.querydsl.sql.RelationalPathBase<MonthlyEntity> {

    private static final long serialVersionUID = 217346517;

    public static final QMonthly monthly = new QMonthly("MONTHLY");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public final com.querydsl.sql.PrimaryKey<MonthlyEntity> sysPk10142 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<PaymentTypeEntity> monthlyPaymentTypeFk = createForeignKey(employeeid, "EMPLOYEEID");

    public QMonthly(String variable) {
        super(MonthlyEntity.class, forVariable(variable), "PUBLIC", "MONTHLY");
        addMetadata();
    }

    public QMonthly(String variable, String schema, String table) {
        super(MonthlyEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMonthly(String variable, String schema) {
        super(MonthlyEntity.class, forVariable(variable), schema, "MONTHLY");
        addMetadata();
    }

    public QMonthly(Path<? extends MonthlyEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MONTHLY");
        addMetadata();
    }

    public QMonthly(PathMetadata metadata) {
        super(MonthlyEntity.class, metadata, "PUBLIC", "MONTHLY");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(salary, ColumnMetadata.named("SALARY").withIndex(2).ofType(Types.INTEGER).withSize(32));
    }

}

