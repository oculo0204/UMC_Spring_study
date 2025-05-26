package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.web.dto.store.AddMissionInStoreRequestDto;
import umc.spring.web.dto.store.MissionStoreResponseDto;
import umc.spring.web.dto.users.UserRequestDTO;

public interface MissionService {
    public Mission addMissionInStore(AddMissionInStoreRequestDto.addDto request);
}
