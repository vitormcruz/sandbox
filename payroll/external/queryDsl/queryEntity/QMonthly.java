package payroll.external.queryDsl.queryEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;
import payroll.external.sql.entity.MonthlyEntity;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMonthly is a Querydsl query type for MonthlyEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMonthly extends com.querydsl.sql.RelationalPathBase<MonthlyEntity> {

    private static final long serialVersionUID = -1683463745;

    public static final QMonthly monthly = new QMonthly("MONTHLY");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public final com.querydsl.sql.PrimaryKey<MonthlyEntity> sysPk10142 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.PaymenttypeEntity> monthlyPaymentTypeFk = createForeignKey(employeeid, "EMPLOYEEID");

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

