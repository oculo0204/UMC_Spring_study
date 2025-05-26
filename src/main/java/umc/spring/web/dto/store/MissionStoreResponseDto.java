package umc.spring.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionStoreResponseDto {
    private Long missionId;
    private Integer price;
    private Integer point;
    private Long storeId;
}
