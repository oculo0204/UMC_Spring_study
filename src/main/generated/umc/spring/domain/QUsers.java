package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = 1160272092L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsers users = new QUsers("users");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final QAddress address;

    public final DateTimePath<java.time.LocalDateTime> birthdate = createDateTime("birthdate", java.time.LocalDateTime.class);

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc.spring.domain.enums.Gender> gender = createEnum("gender", umc.spring.domain.enums.Gender.class);

    public final StringPath image = createString("image");

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.time.LocalDateTime> pointDate = createDateTime("pointDate", java.time.LocalDateTime.class);

    public final StringPath pointStatus = createString("pointStatus");

    public final ListPath<umc.spring.domain.mapping.Prefer, umc.spring.domain.mapping.QPrefer> prefers = this.<umc.spring.domain.mapping.Prefer, umc.spring.domain.mapping.QPrefer>createList("prefers", umc.spring.domain.mapping.Prefer.class, umc.spring.domain.mapping.QPrefer.class, PathInits.DIRECT2);

    public final QReview review;

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<umc.spring.domain.enums.SocialType> socialType = createEnum("socialType", umc.spring.domain.enums.SocialType.class);

    public final ListPath<umc.spring.domain.mapping.Solve, umc.spring.domain.mapping.QSolve> solves = this.<umc.spring.domain.mapping.Solve, umc.spring.domain.mapping.QSolve>createList("solves", umc.spring.domain.mapping.Solve.class, umc.spring.domain.mapping.QSolve.class, PathInits.DIRECT2);

    public final EnumPath<umc.spring.domain.enums.UserStatus> status = createEnum("status", umc.spring.domain.enums.UserStatus.class);

    public final ListPath<Store, QStore> stores = this.<Store, QStore>createList("stores", Store.class, QStore.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath userType = createString("userType");

    public final NumberPath<Long> wholePoint = createNumber("wholePoint", Long.class);

    public QUsers(String variable) {
        this(Users.class, forVariable(variable), INITS);
    }

    public QUsers(Path<? extends Users> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsers(PathMetadata metadata, PathInits inits) {
        this(Users.class, metadata, inits);
    }

    public QUsers(Class<? extends Users> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

