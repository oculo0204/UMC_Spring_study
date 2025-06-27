package umc.spring.web.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.enums.Role;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class UserRequestDTO {
    @Getter
    @Setter
    public static class JoinDto{
        @NotBlank
        String name;
        @NotNull
        String nickName;
        @NotNull
        String gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String detailAddress;
        @NotBlank
        @Email
        String email;
        @NotBlank
        String password;
        @NotNull
        Role role;

        String socialType;

        @ExistCategories
        List<Long> preferCategory;
    }
}
