package umc.spring.web.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(
            description = "회원가입 요청 DTO",
            example = "{\n" +
                    "  \"name\": \"장서원\",\n" +
                    "  \"nickName\": \"애스터\",\n" +
                    "  \"gender\": \"MALE\",\n" +
                    "  \"birthYear\": 2002,\n" +
                    "  \"birthMonth\": 2,\n" +
                    "  \"birthDay\": 2,\n" +
                    "  \"address\": \"서울시 강남구\",\n" +
                    "  \"detailAddress\": \"101동 1001호\",\n" +
                    "  \"email\": \"admin@admin.com\",\n" +
                    "  \"password\": \"test1234!\",\n" +
                    "  \"role\": \"ADMIN\",\n" +
                    "  \"socialType\": null,\n" +
                    "  \"preferCategory\": [1, 2, 3]\n" +
                    "}"
    )
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
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        String email;
        @NotBlank(message = "패스워드는 필수입니다.")
        String password;
        @NotNull
        Role role;

        String socialType;

        @ExistCategories
        List<Long> preferCategory;
    }

    @Getter
    @Setter
    @Schema(
            description = "로그인 요청 DTO",
            example = "{\n" +
                    "  \"email\": \"admin@admin.com\",\n" +
                    "  \"password\": \"test1234!\"\n" +
                    "}"
    )
    public static class LoginRequestDTO{
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        String email;
        @NotBlank(message = "패스워드는 필수입니다.")
        String password;
    }
}
