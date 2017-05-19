package payroll.external.queryDsl.queryEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;
import payroll.external.sql.entity.PaymenttypeEntity;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QPaymenttype is a Querydsl query type for PaymenttypeEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QPaymenttype extends com.querydsl.sql.RelationalPathBase<PaymenttypeEntity> {

    private static final long serialVersionUID = 76357170;

    public static final QPaymenttype paymenttype = new QPaymenttype("PAYMENTTYPE");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final com.querydsl.sql.PrimaryKey<PaymenttypeEntity> sysPk10134 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.EmployeeEntity> paymentTypeEmployeeFk = createForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.CommissionEntity> _comissionPaymentTypeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.MonthlyEntity> _monthlyPaymentTypeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.HourlyEntity> _hourlyPaymentTypeFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public QPaymenttype(String variable) {
        super(PaymenttypeEntity.class, forVariable(variable), "PUBLIC", "PAYMENTTYPE");
        addMetadata();
    }

    public QPaymenttype(String variable, String schema, String table) {
        super(PaymenttypeEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QPaymenttype(String variable, String schema) {
        super(PaymenttypeEntity.class, forVariable(variable), schema, "PAYMENTTYPE");
        addMetadata();
    }

    public QPaymenttype(Path<? extends PaymenttypeEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "PAYMENTTYPE");
        addMetadata();
    }

    public QPaymenttype(PathMetadata metadata) {
        super(PaymenttypeEntity.class, metadata, "PUBLIC", "PAYMENTTYPE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
    }

}

