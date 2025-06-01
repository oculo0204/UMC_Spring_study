package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.review.ReviewResponseDto;

public interface ReviewRepositoryCustom {
    ReviewResponseDto createReviews(Long userId, Long storeId, String detail, Double starRate, Long missionId);
}
