package umc.spring.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.*;
import umc.spring.web.dto.review.ReviewResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private JPAQueryFactory queryFactory;
    private EntityManager em;

    @Override
    @Transactional
    public ReviewResponseDto createReviews(Long userId, Long storeId, String detail, Double starRate, Long missionId){
        // 1. 리뷰 저장
        Review review = Review.builder()
                .users(em.getReference(Users.class, userId))
                .store(em.getReference(Store.class, storeId))
                .detail(detail)
                .starRate(BigDecimal.valueOf(starRate))
                .mission(em.getReference(Mission.class, missionId))
                .build();
        em.persist(review);


        // 3. 유저 이름 조회 및 반환
        String userName = queryFactory
                .select(QUsers.users.name)
                .from(QUsers.users)
                .where(QUsers.users.userId.eq(userId))
                .fetchOne();

        return ReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .username(userName)
                .detail(review.getDetail())
                .starRate(review.getStarRate().doubleValue())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
