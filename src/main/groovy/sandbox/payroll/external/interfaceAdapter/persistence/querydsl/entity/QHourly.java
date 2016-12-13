package sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import sandbox.payroll.payment.Hourly;
import sandbox.payroll.payment.TimeCard;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QHourly is a Querydsl query type for Hourly
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHourly extends EntityPathBase<Hourly> {

    private static final long serialVersionUID = 899544547L;

    public static final QHourly hourly = new QHourly("hourly");

    public final QGenericPayment _super = new QGenericPayment(this);

    public final NumberPath<Integer> hourRate = createNumber("hourRate", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final SetPath<TimeCard, QTimeCard> paymentInfos = this.<TimeCard, QTimeCard>createSet("paymentAttachments", TimeCard.class, QTimeCard.class, PathInits.DIRECT2);

    public QHourly(String variable) {
        super(Hourly.class, forVariable(variable));
    }

    public QHourly(Path<? extends Hourly> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHourly(PathMetadata metadata) {
        super(Hourly.class, metadata);
    }

}

