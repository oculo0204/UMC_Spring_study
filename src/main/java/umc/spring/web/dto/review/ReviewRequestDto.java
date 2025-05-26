package umc.spring.web.dto.review;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

public class ReviewRequestDto {
    @Getter
    public static class makeDto {
        @NotNull
        private String detail;
        @NotNull
        private BigDecimal starRate;
        @NotNull
        private Long missionId;
        @NotNull
        private Long storeId;
        @NotNull
        private Long usersId;
        private List<String> reviewImgUrls;
    }
}
