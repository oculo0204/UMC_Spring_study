package umc.spring.web.dto.users;

import lombok.Getter;

import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class JoinDto{
        String name;
        String nickName;
        String gender;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;
        String address;
        String detailAddress;
        String socialType;
        List<Long> preferCategory;
    }
}
