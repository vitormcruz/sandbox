package sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import sandbox.payroll.imp.EmployeeImp;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QEmployeeImp is a Querydsl query type for EmployeeImp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<EmployeeImp> {

    private static final long serialVersionUID = 63049228L;

    public static final QEmployee employee = new QEmployee("employee");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QEmployee(String variable) {
        super(EmployeeImp.class, forVariable(variable));
    }

    public QEmployee(Path<? extends EmployeeImp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(EmployeeImp.class, metadata);
    }

}

