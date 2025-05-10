package umc.spring.repository.MissionRepository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.QStoreCategory;
import umc.spring.domain.QUsers;
import umc.spring.domain.mapping.QSolve;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QStoreType;
import com.querydsl.core.types.dsl.DateTimeExpression;


import java.time.LocalDateTime;
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
                        solve.status,
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

    public List<?> findMissions(String region, Long cursorValue, Long currentUserId) {
        // Q클래스 생성
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QStoreType storeType = QStoreType.storeType;
        QStoreCategory storeCategory = QStoreCategory.storeCategory;
        QSolve solve = QSolve.solve;

        // 현재 시간에서 7일 이전 시간 구하기
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);

        // QueryDSL을 사용한 쿼리 작성
        JPAQuery<?> query = new JPAQuery<>(em);

        // 결과 조회
        List<?> result = query.select(
                        mission.missionId,
                        store.name,
                        mission.point,
                        mission.price,
                        storeCategory.name,
                        mission.createdAt
                )
                .from(mission)
                .join(store).on(mission.store.storeId.eq(store.storeId)) // storeId 연결
                .join(store.storeTypes, storeType).on(store.storeId.eq(storeType.store.storeId)) // storeTypes 연결
                .join(storeType.storeCategory, storeCategory).on(storeType.storeCategory.storeCategoryId.eq(storeCategory.storeCategoryId)) // storeCategory 연결
                .leftJoin(solve).on(solve.mission.missionId.eq(mission.missionId)) // missionId 연결
                .where(store.region.eq(region)) // 지역 필터
                .where(mission.createdAt.after(sevenDaysAgo)) // createdAt이 7일 이내인 미션 필터
                .where(mission.missionId.lt(cursorValue)) // 페이징 처리 (cursor 값)
                .where(mission.missionId.notIn(
                        JPAExpressions.select(solve.mission.missionId)
                                .from(solve)
                                .where(solve.users.userId.eq(currentUserId)) // 현재 사용자가 해결한 미션 제외
                ))
                .orderBy(mission.missionId.desc()) // 최신 미션 우선으로 정렬
                .limit(10) // 상위 10개 미션
                .fetch(); // 결과를 가져옴

        return result;
    }
}