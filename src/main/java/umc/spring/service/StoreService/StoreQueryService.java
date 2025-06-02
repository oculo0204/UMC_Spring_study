package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.store.StoreRequestDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    Store createStore(StoreRequestDto.JoinDto storeRequestDto);
    Review createReview(ReviewRequestDto.makeDto requestDto);
    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndStarSum(String name, Long starSum);
    Page<Review> getReviewList(Long StoreId, Integer page);

    Page<Mission> getMissionList(Long storeId, Integer page);
}
