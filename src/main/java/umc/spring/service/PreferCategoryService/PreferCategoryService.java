package umc.spring.service.PreferCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.PreferCategoryRepository.PreferCategoryRepository;
import umc.spring.web.dto.store.StoreRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferCategoryService {

    private final PreferCategoryRepository preferCategoryRepository;

    public boolean allExistByIds(List<Long> ids) {
        return ids.stream().allMatch(preferCategoryRepository::existsById);
    }
}