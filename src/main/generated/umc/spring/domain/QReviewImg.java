package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewImg is a Querydsl query type for ReviewImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewImg extends EntityPathBase<ReviewImg> {

    private static final long serialVersionUID = 1570441759L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewImg reviewImg = new QReviewImg("reviewImg");

    public final StringPath imageId = createString("imageId");

    public final StringPath imageUrl = createString("imageUrl");

    public final QReview review;

    public QReviewImg(String variable) {
        this(ReviewImg.class, forVariable(variable), INITS);
    }

    public QReviewImg(Path<? extends ReviewImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewImg(PathMetadata metadata, PathInits inits) {
        this(ReviewImg.class, metadata, inits);
    }

    public QReviewImg(Class<? extends ReviewImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

