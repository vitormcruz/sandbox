package payroll.external.queryDsl.queryEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;
import payroll.external.sql.entity.HourlyEntity;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QHourly is a Querydsl query type for HourlyEntity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QHourly extends com.querydsl.sql.RelationalPathBase<HourlyEntity> {

    private static final long serialVersionUID = 1417832549;

    public static final QHourly hourly = new QHourly("HOURLY");

    public final NumberPath<Long> employeeid = createNumber("employeeid", Long.class);

    public final NumberPath<Integer> hourrate = createNumber("hourrate", Integer.class);

    public final com.querydsl.sql.PrimaryKey<HourlyEntity> sysPk10138 = createPrimaryKey(employeeid);

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.PaymenttypeEntity> hourlyPaymentTypeFk = createForeignKey(employeeid, "EMPLOYEEID");

    public final com.querydsl.sql.ForeignKey<payroll.external.sql.entity.TimecardEntity> _timeCardCommisionFk = createInvForeignKey(employeeid, "EMPLOYEEID");

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

