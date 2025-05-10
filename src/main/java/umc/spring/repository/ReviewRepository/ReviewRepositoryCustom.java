package umc.spring.repository.ReviewRepository;

import umc.spring.web.dto.review.ReviewResponseDto;

public interface ReviewRepositoryCustom {
    ReviewResponseDto createReviews(Long userId, Long storeId, String detail, Double starRate, Long missionId);
}
