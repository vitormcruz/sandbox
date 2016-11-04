package sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import sandbox.payroll.imp.GenericPaymentData;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QGenericPayment is a Querydsl query type for GenericPayment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGenericPayment extends EntityPathBase<GenericPaymentData> {

    private static final long serialVersionUID = 485333409L;

    public static final QGenericPayment genericPayment = new QGenericPayment("genericPayment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QGenericPayment(String variable) {
        super(GenericPaymentData.class, forVariable(variable));
    }

    public QGenericPayment(Path<? extends GenericPaymentData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenericPayment(PathMetadata metadata) {
        super(GenericPaymentData.class, metadata);
    }

}

