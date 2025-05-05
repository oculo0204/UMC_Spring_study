package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPreferCategory is a Querydsl query type for PreferCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPreferCategory extends EntityPathBase<PreferCategory> {

    private static final long serialVersionUID = -1801853222L;

    public static final QPreferCategory preferCategory = new QPreferCategory("preferCategory");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPreferCategory(String variable) {
        super(PreferCategory.class, forVariable(variable));
    }

    public QPreferCategory(Path<? extends PreferCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPreferCategory(PathMetadata metadata) {
        super(PreferCategory.class, metadata);
    }

}

