package payroll.external.queryDsl.queryEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.sql.ColumnMetadata;
import payroll.external.sql.entity.SalesreceiptEntity;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.*;




/**
 * QSalesreceipt is a Querydsl query type for SalesreceiptEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSalesreceipt extends com.querydsl.sql.RelationalPathBase<SalesreceiptEntity> {

    private static final long serialVersionUID = 405590176;

    public static final QSalesreceipt salesreceipt = new QSalesreceipt("SALESRECEIPT");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.sql.Timestamp> date = createDateTime("date", java.sql.Timestamp.class);

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.querydsl.sql.PrimaryKey<SalesreceiptEntity> sysPk10150 = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.CommissionEntity> salesReceiptCommisionFk = createForeignKey(employeeid, "EMPLOYEEID");

    public QSalesreceipt(String variable) {
        super(SalesreceiptEntity.class, forVariable(variable), "PUBLIC", "SALESRECEIPT");
        addMetadata();
    }

    public QSalesreceipt(String variable, String schema, String table) {
        super(SalesreceiptEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSalesreceipt(String variable, String schema) {
        super(SalesreceiptEntity.class, forVariable(variable), schema, "SALESRECEIPT");
        addMetadata();
    }

    public QSalesreceipt(Path<? extends SalesreceiptEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SALESRECEIPT");
        addMetadata();
    }

    public QSalesreceipt(PathMetadata metadata) {
        super(SalesreceiptEntity.class, metadata, "PUBLIC", "SALESRECEIPT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(amount, ColumnMetadata.named("AMOUNT").withIndex(3).ofType(Types.INTEGER).withSize(32));
        addMetadata(date, ColumnMetadata.named("DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26));
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(4).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
    }

}

