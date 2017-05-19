package com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.EmployeeEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.PaymentTypeEntity;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QEmployee is a Querydsl query type for EmployeeEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QEmployee extends com.querydsl.sql.RelationalPathBase<EmployeeEntity> {

    private static final long serialVersionUID = 2085117260;

    public static final QEmployee employee = new QEmployee("EMPLOYEE");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<EmployeeEntity> sysPk10131 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<PaymentTypeEntity> _paymentTypeEmployeeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public QEmployee(String variable) {
        super(EmployeeEntity.class, forVariable(variable), "PUBLIC", "EMPLOYEE");
        addMetadata();
    }

    public QEmployee(String variable, String schema, String table) {
        super(EmployeeEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QEmployee(String variable, String schema) {
        super(EmployeeEntity.class, forVariable(variable), schema, "EMPLOYEE");
        addMetadata();
    }

    public QEmployee(Path<? extends EmployeeEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "EMPLOYEE");
        addMetadata();
    }

    public QEmployee(PathMetadata metadata) {
        super(EmployeeEntity.class, metadata, "PUBLIC", "EMPLOYEE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("ADDRESS").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(email, ColumnMetadata.named("EMAIL").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(2).ofType(Types.VARCHAR).withSize(255));
    }

}

