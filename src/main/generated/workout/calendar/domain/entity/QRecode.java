package workout.calendar.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecode is a Querydsl query type for Recode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecode extends EntityPathBase<Recode> {

    private static final long serialVersionUID = 5736608L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecode recode = new QRecode("recode");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath date = createString("date");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<Performance, QPerformance> performance = this.<Performance, QPerformance>createList("performance", Performance.class, QPerformance.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalWeight = createNumber("totalWeight", Integer.class);

    public final QUser user;

    public QRecode(String variable) {
        this(Recode.class, forVariable(variable), INITS);
    }

    public QRecode(Path<? extends Recode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecode(PathMetadata metadata, PathInits inits) {
        this(Recode.class, metadata, inits);
    }

    public QRecode(Class<? extends Recode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

