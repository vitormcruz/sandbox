package payroll.external.queryDsl.queryEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;
import payroll.external.sql.entity.CommissionEntity;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCommission is a Querydsl query type for CommissionEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCommission extends com.querydsl.sql.RelationalPathBase<CommissionEntity> {

    private static final long serialVersionUID = -13449505;

    public static final QCommission commission = new QCommission("COMMISSION");

    public final NumberPath<Integer> commissionrate = createNumber("commissionrate", Integer.class);

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final com.querydsl.sql.PrimaryKey<CommissionEntity> sysPk10146 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.PaymenttypeEntity> comissionPaymentTypeFk = createForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.SalesreceiptEntity> _salesReceiptCommisionFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public QCommission(String variable) {
        super(CommissionEntity.class, forVariable(variable), "PUBLIC", "COMMISSION");
        addMetadata();
    }

    public QCommission(String variable, String schema, String table) {
        super(CommissionEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCommission(String variable, String schema) {
        super(CommissionEntity.class, forVariable(variable), schema, "COMMISSION");
        addMetadata();
    }

    public QCommission(Path<? extends CommissionEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "COMMISSION");
        addMetadata();
    }

    public QCommission(PathMetadata metadata) {
        super(CommissionEntity.class, metadata, "PUBLIC", "COMMISSION");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(commissionrate, ColumnMetadata.named("COMMISSIONRATE").withIndex(2).ofType(Types.INTEGER).withSize(32));
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
    }

}

