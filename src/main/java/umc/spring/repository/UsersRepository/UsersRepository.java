package umc.spring.repository.UsersRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>, UsersRepositoryCustom {
    Optional<Users> findByEmail(String email);
}
