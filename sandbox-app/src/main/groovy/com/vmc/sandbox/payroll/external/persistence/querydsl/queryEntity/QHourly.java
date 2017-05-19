package com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.sql.ColumnMetadata;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.HourlyEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.PaymentTypeEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.TimeCardEntity;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QHourly is a Querydsl query type for HourlyEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QHourly extends com.querydsl.sql.RelationalPathBase<HourlyEntity> {

    private static final long serialVersionUID = -737608305;

    public static final QHourly hourly = new QHourly("HOURLY");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Integer> hourrate = createNumber("hourrate", Integer.class);

    public final com.querydsl.sql.PrimaryKey<HourlyEntity> sysPk10138 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<PaymentTypeEntity> hourlyPaymentTypeFk = createForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<TimeCardEntity> _timeCardCommisionFk = createInvForeignKey(employeeid, "EMPLOYEEID");

    public QHourly(String variable) {
        super(HourlyEntity.class, forVariable(variable), "PUBLIC", "HOURLY");
        addMetadata();
    }

    public QHourly(String variable, String schema, String table) {
        super(HourlyEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QHourly(String variable, String schema) {
        super(HourlyEntity.class, forVariable(variable), schema, "HOURLY");
        addMetadata();
    }

    public QHourly(Path<? extends HourlyEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "HOURLY");
        addMetadata();
    }

    public QHourly(PathMetadata metadata) {
        super(HourlyEntity.class, metadata, "PUBLIC", "HOURLY");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(hourrate, ColumnMetadata.named("HOURRATE").withIndex(2).ofType(Types.INTEGER).withSize(32));
    }

}

