package umc.spring.repository.storeRepository;

import umc.spring.domain.Address;
import umc.spring.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
    Optional<Address> findQueryAddressAndDetailAddress(String address, String detailAddress);

}