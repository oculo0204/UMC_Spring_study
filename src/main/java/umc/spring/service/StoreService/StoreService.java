package umc.spring.service.StoreService;

import umc.spring.web.dto.store.StoreRequestDto;

public interface StoreService {
    void createStore(StoreRequestDto storeRequestDto);
}
