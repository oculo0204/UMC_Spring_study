package umc.spring.web.dto.store;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AddMissionInStoreRequestDto {
    @Getter
    public static class addDto {
        @NotNull
        private Integer price;
        @NotNull
        private Integer point;
        @NotNull
        private Long storeId;
    }
}
