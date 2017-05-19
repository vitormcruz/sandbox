package com.vmc.sandbox.payroll.external.persistence.querydsl.queryEntity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.sql.ColumnMetadata;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.HourlyEntity;
import com.vmc.sandbox.payroll.external.persistence.querydsl.entity.TimeCardEntity;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QTimeCard is a Querydsl query type for TimeCardEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTimeCard extends com.querydsl.sql.RelationalPathBase<TimeCardEntity> {

    private static final long serialVersionUID = 56914651;

    public static final QTimeCard timeCard = new QTimeCard("TIME_CARD");

    public final DateTimePath<java.sql.Timestamp> date = createDateTime("date", java.sql.Timestamp.class);

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Integer> hours = createNumber("hours", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.querydsl.sql.PrimaryKey<TimeCardEntity> sysPk10154 = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<HourlyEntity> timeCardCommisionFk = createForeignKey(employeeid, "EMPLOYEEID");

    public QTimeCard(String variable) {
        super(TimeCardEntity.class, forVariable(variable), "PUBLIC", "TIME_CARD");
        addMetadata();
    }

    public QTimeCard(String variable, String schema, String table) {
        super(TimeCardEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTimeCard(String variable, String schema) {
        super(TimeCardEntity.class, forVariable(variable), schema, "TIME_CARD");
        addMetadata();
    }

    public QTimeCard(Path<? extends TimeCardEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TIME_CARD");
        addMetadata();
    }

    public QTimeCard(PathMetadata metadata) {
        super(TimeCardEntity.class, metadata, "PUBLIC", "TIME_CARD");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(date, ColumnMetadata.named("DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26));
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(4).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(hours, ColumnMetadata.named("HOURS").withIndex(3).ofType(Types.INTEGER).withSize(32));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
    }

}

