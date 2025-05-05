package umc.spring.repository.MissionRepository;

import java.util.List;

public interface MissionRepositoryCustom {

    List<Object[]> findOngoingMissions(Long userId, Long cursor, int limit);

    List<Object[]> findCompletedMissions(Long userId, Long cursor, int limit);
}
