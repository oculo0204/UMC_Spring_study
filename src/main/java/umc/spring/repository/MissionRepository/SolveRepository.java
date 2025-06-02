package umc.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.StoreCategory;
import umc.spring.domain.mapping.Solve;

import java.util.List;

@Repository
public interface SolveRepository extends JpaRepository<Solve, Long> {
    List<Solve> findByMission_MissionIdIn(List<Long> missionIds);


}
