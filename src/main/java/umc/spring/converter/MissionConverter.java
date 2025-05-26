package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.store.MissionStoreResponseDto;

public class MissionConverter {
    public static MissionStoreResponseDto toResultDTO(Mission mission) {
        return new MissionStoreResponseDto(
                mission.getMissionId(),
                mission.getPrice(),
                mission.getPoint(),
                mission.getStore().getStoreId()
        );
    }
}

