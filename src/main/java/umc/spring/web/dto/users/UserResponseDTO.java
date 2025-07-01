package umc.spring.web.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResultDTO {
        private Long userId;
        private String name;
        private String nickname;
        private String email;
        private String phoneNumber;
        private String gender;
        private LocalDateTime birthdate;
        private String userType;
        private String pointStatus;
        private Long wholePoint;
        private String socialType;
        private LocalDateTime createdAt;
        private String address;
        private String detailAddress;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResultDTO {
        Long userId;
        String accessToken;
    }
}
