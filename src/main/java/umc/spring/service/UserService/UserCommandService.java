package umc.spring.service.UserService;

import umc.spring.domain.Users;
import umc.spring.web.dto.users.UserRequestDTO;

public interface UserCommandService {
    public Users joinUser(UserRequestDTO.JoinDto request);

}
