package umc.spring.repository.storeRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Address;
import umc.spring.domain.QAddress;
import umc.spring.domain.QStore;
import umc.spring.domain.Store;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QStore store = QStore.store;
    private final QAddress address = QAddress.address1;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.and(store.name.eq(name));
        }

        if (score != null) {
            predicate.and(store.starSum.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(store)
                .where(predicate)
                .fetch();
    }

    @Override
    public Optional<Address> findQueryAddressAndDetailAddress(String addressStr, String detailAddressStr) {
        Address result = jpaQueryFactory
                .selectFrom(address)
                .where(
                        address.address.eq(addressStr),
                        address.detailAddress.eq(detailAddressStr)
                )
                .fetchOne();

        return Optional.ofNullable(result);
    }

}
