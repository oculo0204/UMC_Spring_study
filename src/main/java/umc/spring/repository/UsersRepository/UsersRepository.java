package umc.spring.repository.UsersRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long>, UsersRepositoryCustom {
}
