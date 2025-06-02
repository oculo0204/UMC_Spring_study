package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.domain.mapping.Solve;
import umc.spring.domain.mapping.StoreType;
import umc.spring.repository.AddressRepository.AddressRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.MissionRepository.SolveRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.UsersRepository.UsersRepository;
import umc.spring.repository.storeRepository.StoreCategoryRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.repository.storeRepository.StoreTypeRepository;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.store.StoreRequestDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;
    private final StoreCategoryRepository storeCategoryRepository;
    private final MissionRepository missionRepository;
    private final SolveRepository solveRepository;


    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndStarSum(String name, Long starSum) {
        // 동적 쿼리 메서드 호출: name과 starSum에 맞는 데이터를 필터링
        Float starSumFloat = starSum.floatValue();
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, starSumFloat);

        // 출력: filteredStores의 각 Store 객체 출력
        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }
    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
    @Override
    @Transactional
    public Store createStore(StoreRequestDto.JoinDto request) {
        Store newStore = StoreConverter.toStore(request);

        // 주소 처리 (기존 코드 유지)
        Address address = storeRepository.findQueryAddressAndDetailAddress(request.getAddress(), request.getDetailAddress())
                .orElseGet(() -> {
                    Address newAddress = StoreConverter.toAddress(request);
                    return addressRepository.save(newAddress);
                });
        newStore.setAddress(address);

        // 사용자 처리 (기존 코드 유지)
        Users user = usersRepository.findById(request.getUsersId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + request.getUsersId()));
        newStore.setUsers(user);

        // StoreCategory 엔티티 조회
        List<StoreCategory> categories = request.getStoreCategoryIds().stream()
                .map(id -> storeCategoryRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("StoreCategory not found: " + id)))
                .collect(Collectors.toList());

        // StoreType 중간 엔티티 생성 및 할당
        List<StoreType> storeTypes = categories.stream()
                .map(category -> {
                    StoreType storeType = new StoreType();
                    storeType.setStore(newStore);
                    storeType.setStoreCategory(category);
                    return storeType;
                })
                .collect(Collectors.toList());

        newStore.setStoreTypes(storeTypes);

        storeRepository.save(newStore);

        return newStore;
    }


    @Override
    @Transactional
    public Review createReview(ReviewRequestDto.makeDto request) {
        Review newReview = ReviewConverter.toMakeResultDTO(request);
        //store
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()->new IllegalArgumentException("해당 가게가 존재하지 않습니다. id=" + request.getStoreId()));
        newReview.setStore(store);
        //user
        Users user = usersRepository.findById(request.getUsersId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + request.getUsersId()));
        newReview.setUsers(user);
        reviewRepository.save(newReview);
        return newReview;
    }

    @Override
    @Transactional
    public Page<Mission> getMissionList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();
        Page<Mission> StorePage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
    public Map<Long, Solve> getSolvesForMissions(List<Long> missionIds) {
        List<Solve> solves = solveRepository.findByMission_MissionIdIn(missionIds);
        return solves.stream()
                .collect(Collectors.toMap(s -> s.getMission().getMissionId(), s -> s));
    }

}
