package umc.spring.converter;

import umc.spring.domain.Address;
import umc.spring.domain.Store;
import umc.spring.domain.Users;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

import java.time.LocalDateTime;

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

}
