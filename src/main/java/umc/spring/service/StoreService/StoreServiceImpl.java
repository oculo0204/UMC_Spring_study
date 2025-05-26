package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.repository.AddressRepository.AddressRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.UsersRepository.UsersRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.store.StoreRequestDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDto.JoinDto request) {
        Store newStore = StoreConverter.toStore(request);
        //주소 없으면 새로 생성 있으면 가져와서 연결
        Address address= storeRepository.findQueryAddressAndDetailAddress(request.getAddress(),request.getDetailAddress())
                        .orElseGet(()->{
                            Address newAddress = StoreConverter.toAddress(request);
                            return addressRepository.save(newAddress);
                        });
        newStore.setAddress(address);
        // usersId로 Users 엔티티 조회 후 할당
        Users user = usersRepository.findById(request.getUsersId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + request.getUsersId()));
        newStore.setUsers(user);
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
