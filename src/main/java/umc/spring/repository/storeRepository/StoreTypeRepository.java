package umc.spring.repository.storeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.StoreType;

@Repository
public interface StoreTypeRepository extends JpaRepository<StoreType, Long> {

}
