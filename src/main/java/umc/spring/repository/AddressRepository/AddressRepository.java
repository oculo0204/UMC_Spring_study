package umc.spring.repository.AddressRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {
}
