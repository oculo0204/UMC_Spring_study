package umc.spring.web.dto.temp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TempRequest {

    @NotBlank(message = "가게 이름은 필수입니다.")
    private String name;

    @NotNull(message = "영업 시간은 필수입니다.")
    private LocalDateTime businessHours;

    private BigDecimal starSum; // 별점 총합은 요청 시 안 들어올 수도 있음 (optional)

    @NotBlank(message = "지역은 필수입니다.")
    private String region;

    @NotNull(message = "주소 ID는 필수입니다.")
    private Long addressId;

    @NotNull(message = "유저 ID는 필수입니다.")
    private Long userId;

    @NotNull(message = "스토어 타입 ID 리스트는 필수입니다.")
    private List<Long> storeTypeIds;

}
