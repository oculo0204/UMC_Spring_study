package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Store;
import umc.spring.repository.storeRepository.StoreRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndStarSum(String name, Long starSum) {
        // 동적 쿼리 메서드 호출: name과 starSum에 맞는 데이터를 필터링
        Float starSumFloat = starSum.floatValue();
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, starSumFloat);

        // 출력: filteredStores의 각 Store 객체 출력
        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

}