package sandbox.payroll.external.interfaceAdapter.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import sandbox.payroll.EmployeeImp;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<EmployeeImp> {

    private static final long serialVersionUID = -771407557L;

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

