package sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import sandbox.payroll.payment.Monthly;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QMonthly is a Querydsl query type for Monthly
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonthly extends EntityPathBase<Monthly> {

    private static final long serialVersionUID = -2042748197L;

    public static final QMonthly monthly = new QMonthly("monthly");

    public final QGenericPayment _super = new QGenericPayment(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public QMonthly(String variable) {
        super(Monthly.class, forVariable(variable));
    }

    public QMonthly(Path<? extends Monthly> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonthly(PathMetadata metadata) {
        super(Monthly.class, metadata);
    }

}

