package umc.spring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.domain.Users;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

public interface UserCommandService {
    Users joinUser(UserRequestDTO.JoinDto request);

    UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request);

    UserResponseDTO.UserResultDTO getUserInfo(HttpServletRequest request);
}
