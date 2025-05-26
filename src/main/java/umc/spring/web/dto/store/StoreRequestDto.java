package umc.spring.web.dto.store;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StoreRequestDto {
    @Getter
    public static class JoinDto {
        @NotNull
        private String name;
        @NotNull
        private LocalDateTime businessHours;
        @NotNull
        private String region;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String detailAddress;
        private Long usersId;
        @NotNull
        private List<Long> storeCategoryIds;
    }
}
