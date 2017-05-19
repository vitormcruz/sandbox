package payroll.external.queryDsl.queryEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;
import payroll.external.sql.entity.TimecardEntity;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTimecard is a Querydsl query type for TimecardEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTimecard extends com.querydsl.sql.RelationalPathBase<TimecardEntity> {

    private static final long serialVersionUID = -732126191;

    public static final QTimecard timecard = new QTimecard("TIMECARD");

    public final DateTimePath<java.sql.Timestamp> date = createDateTime("date", java.sql.Timestamp.class);

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Integer> hours = createNumber("hours", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.querydsl.sql.PrimaryKey<TimecardEntity> sysPk10154 = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.HourlyEntity> timeCardCommisionFk = createForeignKey(employeeid, "EMPLOYEEID");

    public QTimecard(String variable) {
        super(TimecardEntity.class, forVariable(variable), "PUBLIC", "TIMECARD");
        addMetadata();
    }

    public QTimecard(String variable, String schema, String table) {
        super(TimecardEntity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTimecard(String variable, String schema) {
        super(TimecardEntity.class, forVariable(variable), schema, "TIMECARD");
        addMetadata();
    }

    public QTimecard(Path<? extends TimecardEntity> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TIMECARD");
        addMetadata();
    }

    public QTimecard(PathMetadata metadata) {
        super(TimecardEntity.class, metadata, "PUBLIC", "TIMECARD");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(date, ColumnMetadata.named("DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26));
        addMetadata(employeeid, ColumnMetadata.named("EMPLOYEEID").withIndex(4).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(hours, ColumnMetadata.named("HOURS").withIndex(3).ofType(Types.INTEGER).withSize(32));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
    }

}

