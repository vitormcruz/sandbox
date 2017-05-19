package payroll.external.queryDsl.queryEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;
import payroll.external.sql.entity.EmployeeEntity;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QEmployee is a Querydsl query type for EmployeeEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QEmployee extends com.querydsl.sql.RelationalPathBase<EmployeeEntity> {

    private static final long serialVersionUID = -1005426014;

    public static final QEmployee employee = new QEmployee("EMPLOYEE");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<EmployeeEntity> sysPk10131 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.PaymenttypeEntity> _paymentTypeEmployeeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

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

