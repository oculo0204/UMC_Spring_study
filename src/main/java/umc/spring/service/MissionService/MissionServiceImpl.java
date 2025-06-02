package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.store.AddMissionInStoreRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Mission addMissionInStore(AddMissionInStoreRequestDto.addDto request){
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("해당 매장이 존재하지 않습니다."));

        Mission mission = Mission.builder()
                .price(request.getPrice())
                .point(request.getPoint())
                .store(store)
                .build();
        missionRepository.save(mission);
        return mission;
    }
    public List<Mission> getMissionsByIds(List<Long> missionIds) {
        return missionRepository.findAllById(missionIds);
    }
}
