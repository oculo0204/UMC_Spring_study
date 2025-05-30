package umc.spring.service.StoreService;

import umc.spring.domain.Store;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndStarSum(String name, Long starSum);
}
