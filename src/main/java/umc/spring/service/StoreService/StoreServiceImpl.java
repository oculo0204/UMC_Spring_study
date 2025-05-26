package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.domain.mapping.StoreType;
import umc.spring.repository.AddressRepository.AddressRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.UsersRepository.UsersRepository;
import umc.spring.repository.storeRepository.StoreCategoryRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.repository.storeRepository.StoreTypeRepository;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.store.StoreRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;
    private final StoreTypeRepository storeTypeRepository;
    private final StoreCategoryRepository storeCategoryRepository;

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
}
