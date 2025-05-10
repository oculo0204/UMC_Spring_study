package umc.spring.web.dto.review;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponseDto {
    private Long reviewId;
    private String username;
    private String detail;
    private Double starRate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
