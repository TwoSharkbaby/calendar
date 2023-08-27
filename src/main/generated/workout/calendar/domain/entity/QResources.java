package workout.calendar.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResources is a Querydsl query type for Resources
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResources extends EntityPathBase<Resources> {

    private static final long serialVersionUID = 431939621L;

    public static final QResources resources = new QResources("resources");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final EnumPath<workout.calendar.domain.RoleType> role = createEnum("role", workout.calendar.domain.RoleType.class);

    public final StringPath urlName = createString("urlName");

    public QResources(String variable) {
        super(Resources.class, forVariable(variable));
    }

    public QResources(Path<? extends Resources> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResources(PathMetadata metadata) {
        super(Resources.class, metadata);
    }

}

