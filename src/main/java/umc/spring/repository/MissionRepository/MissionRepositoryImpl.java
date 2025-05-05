package umc.spring.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.QUsers;
import umc.spring.domain.mapping.QSolve;
import umc.spring.domain.enums.MissionStatus;
import java.util.List;

@Repository
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Object[]> findOngoingMissions(Long userId, Long cursor, int limit) {
        QMission mission = QMission.mission;
        QUsers user = QUsers.users;
        QSolve solve = QSolve.solve;  // 'Solve'로 변경
        QStore store = QStore.store;

        JPAQuery<Object[]> query = new JPAQuery<>(entityManager);

        return query.select(
                        mission.missionId,
                        user.userId,
                        mission.price,
                        mission.point,
                        solve.status  // 'memberMission'을 'solve'로 변경
                )
                .from(mission)
                .join(solve).on(mission.missionId.eq(solve.mission.missionId))
                .join(user).on(solve.users.userId.eq(user.userId))
                .join(store).on(mission.store.storeId.eq(store.storeId))
                .where(
                        user.userId.eq(userId),
                        solve.status.eq(MissionStatus.CHALLENGING),
                        mission.missionId.lt(cursor)
                )
                .orderBy(mission.missionId.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Object[]> findCompletedMissions(Long userId, Long cursor, int limit) {
        QMission mission = QMission.mission;
        QUsers user = QUsers.users;
        QSolve solve = QSolve.solve;  // 'Solve'로 변경
        QStore store = QStore.store;

        JPAQuery<Object[]> query = new JPAQuery<>(entityManager);

        return query.select(
                        mission.missionId,
                        store.name,
                        mission.price,
                        mission.point,
                        solve.status
                )
                .from(mission)
                .join(solve).on(mission.missionId.eq(solve.mission.missionId))
                .join(user).on(solve.users.userId.eq(user.userId))
                .join(store).on(mission.store.storeId.eq(store.storeId))
                .where(
                        user.userId.eq(userId),
                        solve.status.eq("진행완료"),
                        mission.missionId.lt(cursor)
                )
                .orderBy(mission.missionId.desc())
                .limit(limit)
                .fetch();
    }
}