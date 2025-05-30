package umc.spring.converter;

import umc.spring.domain.Address;
import umc.spring.domain.PreferCategory;
import umc.spring.domain.Users;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.UserStatus;
import umc.spring.domain.mapping.Prefer;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(Users users) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(users.getUserId())
                .name(users.getName())
                .nickname(users.getNickname())
                .email(users.getEmail())
                .phoneNumber(users.getPhoneNumber())
                .gender(users.getGender() != null ? users.getGender().name() : null)
                .birthdate(users.getBirthdate())
                .userType(users.getUserType())
                .pointStatus(users.getPointStatus())
                .wholePoint(users.getWholePoint())
                .socialType(users.getSocialType() != null ? users.getSocialType().name() : null)
                .address(users.getAddress() != null ? users.getAddress().getAddress() : null)
                .detailAddress(users.getAddress() != null ? users.getAddress().getDetailAddress() : null)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Users toUsers(UserRequestDTO.JoinDto request) {
        Gender gender;
        try {
            gender = Gender.valueOf(request.getGender().toUpperCase());
        } catch (Exception e) {
            gender = Gender.NONE;
        }

        SocialType socialType;
        try {
            socialType = SocialType.valueOf(request.getSocialType().toUpperCase());
        } catch (Exception e) {
            socialType = SocialType.KAKAO;
        }

        LocalDateTime birthdate = null;
        if (request.getBirthYear() != null && request.getBirthMonth() != null && request.getBirthDay() != null) {
            birthdate = LocalDateTime.of(
                    request.getBirthYear(),
                    request.getBirthMonth(),
                    request.getBirthDay(),
                    0, 0
            );
        }

        return Users.builder()
                .name(request.getName())
                .gender(gender)
                .birthdate(birthdate)
                .wholePoint(0L)
                .pointStatus("NORMAL")
                .userType("GENERAL")
                .status(UserStatus.ACTIVE)
                .socialType(socialType)
                .nickname(request.getNickName())
                .email(null)
                .phoneNumber(null)
                .image(null)
                .build();
    }

    public static List<Prefer> toPreferList(List<PreferCategory> preferCategoryList) {
        return preferCategoryList.stream()
                .map(preferCategory ->
                        Prefer.builder()
                                .preferCategory(preferCategory)
                                .build()
                        ).collect(Collectors.toList());
    }
    public static Address toAddress(UserRequestDTO.JoinDto request) {
        return Address.builder()
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .build();
    }

}
