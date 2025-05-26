package umc.spring.converter;

import umc.spring.domain.Address;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.StoreType;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

import java.time.LocalDateTime;
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
}
