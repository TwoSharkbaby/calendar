package workout.calendar.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPerformance is a Querydsl query type for Performance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerformance extends EntityPathBase<Performance> {

    private static final long serialVersionUID = 62173744L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPerformance performance = new QPerformance("performance");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRecode recode;

    public final NumberPath<Integer> rep = createNumber("rep", Integer.class);

    public final NumberPath<Integer> setCount = createNumber("setCount", Integer.class);

    public final StringPath type = createString("type");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QPerformance(String variable) {
        this(Performance.class, forVariable(variable), INITS);
    }

    public QPerformance(Path<? extends Performance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPerformance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPerformance(PathMetadata metadata, PathInits inits) {
        this(Performance.class, metadata, inits);
    }

    public QPerformance(Class<? extends Performance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recode = inits.isInitialized("recode") ? new QRecode(forProperty("recode"), inits.get("recode")) : null;
    }

}

