package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Users;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserCommandService userCommandService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        Users user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }
}
