package umc.spring.web.dto.store;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StoreResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        private String name;
        private LocalDateTime businessHours;
        private String region;
        private String address;
        private String detailAddress;
        private Long usersId;
        private List<String> storeCategoryIds;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }
}
