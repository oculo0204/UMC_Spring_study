package umc.spring.web.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsersProfileDto {
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private Long wholePoint;
}