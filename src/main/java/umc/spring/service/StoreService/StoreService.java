package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDto;

public interface StoreService {
    public Store createStore(StoreRequestDto.JoinDto storeRequestDto);

    public Review createReview(ReviewRequestDto.makeDto requestDto);
}
