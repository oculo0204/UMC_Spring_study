package umc.spring.repository.UsersRepository;

import umc.spring.web.dto.users.UsersProfileDto;

import java.util.Optional;

public interface UsersRepositoryCustom {
    Optional<UsersProfileDto> findUserProfileById(Long userId);
}
