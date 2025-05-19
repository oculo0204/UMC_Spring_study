package umc.spring.repository.PreferCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.PreferCategory;

public interface PreferCategoryRepository extends JpaRepository<PreferCategory, Long> {

}