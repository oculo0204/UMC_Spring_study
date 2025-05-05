package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSolve is a Querydsl query type for Solve
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSolve extends EntityPathBase<Solve> {

    private static final long serialVersionUID = 1361506803L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSolve solve = new QSolve("solve");

    public final umc.spring.domain.QMission mission;

    public final DateTimePath<java.time.LocalDateTime> solveDate = createDateTime("solveDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> solveId = createNumber("solveId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final EnumPath<umc.spring.domain.enums.MissionStatus> status = createEnum("status", umc.spring.domain.enums.MissionStatus.class);

    public final umc.spring.domain.QUsers users;

    public QSolve(String variable) {
        this(Solve.class, forVariable(variable), INITS);
    }

    public QSolve(Path<? extends Solve> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSolve(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSolve(PathMetadata metadata, PathInits inits) {
        this(Solve.class, metadata, inits);
    }

    public QSolve(Class<? extends Solve> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mission = inits.isInitialized("mission") ? new umc.spring.domain.QMission(forProperty("mission"), inits.get("mission")) : null;
        this.users = inits.isInitialized("users") ? new umc.spring.domain.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

