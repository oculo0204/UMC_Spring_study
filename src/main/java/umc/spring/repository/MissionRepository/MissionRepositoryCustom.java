package umc.spring.repository.MissionRepository;

import java.util.List;

public interface MissionRepositoryCustom {

    List<?> findOngoingMissions(Long userId, Long cursor, int limit);

    List<?> findCompletedMissions(Long userId, Long cursor, int limit);
}
