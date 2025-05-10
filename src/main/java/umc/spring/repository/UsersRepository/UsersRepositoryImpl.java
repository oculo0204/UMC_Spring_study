package umc.spring.repository.UsersRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.QUsers;
import umc.spring.web.dto.users.UsersProfileDto;
import java.util.Optional;

@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UsersProfileDto> findUserProfileById(Long userId) {
        QUsers users = QUsers.users;

        UsersProfileDto result = queryFactory
                .select(Projections.constructor(
                        UsersProfileDto.class,
                        users.userId,
                        users.name,
                        users.email,
                        users.phoneNumber,
                        users.wholePoint
                ))
                .from(users)
                .where(users.userId.eq(userId))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
