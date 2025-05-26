package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.store.StoreRequestDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StroreServiceImpl  implements StoreService{
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public void createStore(StoreRequestDto request) {
        Store store = StoreConverter.toStore(request);

        storeRepository.save(store);
    }
}
