package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Address;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.StoreType;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDto.JoinDto request) {
        return Store.builder()
                .name(request.getName())
                .businessHours(request.getBusinessHours())
                .region(request.getRegion())
                .build();
    }
    public static Address toAddress(StoreRequestDto.JoinDto request) {
        return Address.builder()
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .build();
    }
    public static StoreResponseDto.JoinResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDto.JoinResultDTO.builder()
                .name(store.getName())
                .businessHours(store.getBusinessHours())
                .region(store.getRegion())
                .address(store.getAddress().getAddress())
                .detailAddress(store.getAddress().getDetailAddress())
                .usersId(store.getUsers().getUserId())
                .storeCategoryIds(
                        store.getStoreTypes().stream()
                                .map(storeType -> storeType.getStoreCategory().getName())
                                .collect(Collectors.toList())
                )
                .build();
    }
    public static StoreResponseDto.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUsers().getName())
                .score(review.getStarRate().floatValue())  // BigDecimal → Float
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getDetail())  // body → detail로 변경
                .build();
    }

    public static StoreResponseDto.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDto.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO)
                .collect(Collectors.toList());

        return StoreResponseDto.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
