package com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.sql.ColumnMetadata;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.CommissionEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.SalesReceiptEntity;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QSalesReceipt is a Querydsl query type for SalesReceiptEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSalesReceipt extends com.querydsl.sql.RelationalPathBase<SalesReceiptEntity> {

    private static final long serialVersionUID = 957648426;

    public static final QSalesReceipt salesReceipt = new QSalesReceipt("SALES_RECEIPT");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.sql.Timestamp> date = createDateTime("date", java.sql.Timestamp.class);

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.querydsl.sql.PrimaryKey<SalesReceiptEntity> sysPk10150 = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<CommissionEntity> salesReceiptCommisionFk = createForeignKey(employeeid, "EMPLOYEEID");

    public QSalesReceipt(String variable) {
        super(SalesReceiptEntity.class, forVariable(variable), "PUBLIC", "SALES_RECEIPT");
        addMetadata();
    }

    public QSalesReceipt(String variable, String schema, String table) {
        super(SalesReceiptEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSalesReceipt(String variable, String schema) {
        super(SalesReceiptEntity.class, forVariable(variable), schema, "SALES_RECEIPT");
        addMetadata();
    }

    public QSalesReceipt(Path<? extends SalesReceiptEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SALES_RECEIPT");
        addMetadata();
    }

    public QSalesReceipt(PathMetadata metadata) {
        super(SalesReceiptEntity.class, metadata, "PUBLIC", "SALES_RECEIPT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(amount, ColumnMetadata.named("AMOUNT").withIndex(3).ofType(Types.INTEGER).withSize(32));
        addMetadata(date, ColumnMetadata.named("DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26));
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(4).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
    }

}

