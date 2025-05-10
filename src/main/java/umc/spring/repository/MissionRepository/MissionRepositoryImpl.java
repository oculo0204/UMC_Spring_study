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
    private final EntityManager em;

    @Autowired
    public MissionRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<?> findOngoingMissions(Long userId, Long cursor, int limit) {
        QUsers users = QUsers.users;
        QSolve solve = QSolve.solve;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        JPAQuery<?> query = new JPAQuery<>(em);

        return query.select(
                        mission.missionId,
                        users.userId,
                        mission.price,
                        mission.point,
                        mission.createdAt,
                        store.name
                )
                .from(users)
                .join(solve).on(users.userId.eq(solve.users.userId))
                .join(mission).on(mission.missionId.eq(solve.mission.missionId))
                .join(store).on(mission.store.storeId.eq(store.storeId))
                .where(
                        users.userId.eq(userId),
                        solve.status.eq(MissionStatus.CHALLENGING),
                        mission.missionId.lt(cursor)
                )
                .orderBy(mission.missionId.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<?> findCompletedMissions(Long userId, Long cursor, int limit) {
        QUsers users = QUsers.users;
        QSolve solve = QSolve.solve;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        JPAQuery<?> query = new JPAQuery<>(em);

        return query.select(
                        mission.missionId,
                        users.userId,
                        mission.price,
                        mission.point,
                        mission.createdAt,
                        store.name
                )
                .from(users)
                .join(solve).on(users.userId.eq(solve.users.userId))
                .join(mission).on(mission.missionId.eq(solve.mission.missionId))
                .join(store).on(mission.store.storeId.eq(store.storeId))
                .where(
                        users.userId.eq(userId),
                        solve.status.eq(MissionStatus.COMPLETE),
                        mission.missionId.lt(cursor)
                )
                .orderBy(mission.missionId.desc())
                .limit(limit)
                .fetch();
    }

}