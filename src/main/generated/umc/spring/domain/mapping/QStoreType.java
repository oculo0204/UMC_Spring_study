package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreType is a Querydsl query type for StoreType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreType extends EntityPathBase<StoreType> {

    private static final long serialVersionUID = 1058272111L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreType storeType = new QStoreType("storeType");

    public final umc.spring.domain.QStore store;

    public final umc.spring.domain.QStoreCategory storeCategory;

    public final NumberPath<Long> storetypeId = createNumber("storetypeId", Long.class);

    public QStoreType(String variable) {
        this(StoreType.class, forVariable(variable), INITS);
    }

    public QStoreType(Path<? extends StoreType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreType(PathMetadata metadata, PathInits inits) {
        this(StoreType.class, metadata, inits);
    }

    public QStoreType(Class<? extends StoreType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new umc.spring.domain.QStore(forProperty("store"), inits.get("store")) : null;
        this.storeCategory = inits.isInitialized("storeCategory") ? new umc.spring.domain.QStoreCategory(forProperty("storeCategory")) : null;
    }

}

