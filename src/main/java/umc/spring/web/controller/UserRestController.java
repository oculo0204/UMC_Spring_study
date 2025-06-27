package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.Solve;
import umc.spring.service.MissionService.MissionService;
import umc.spring.service.MissionService.SolveService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.mission.MissionResponseDto;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserCommandService userCommandService;
    private final StoreQueryService storeQueryService;
    private final SolveService solveService;
    private final MissionService missionService;

    @PostMapping("/join")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        Users user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/mission")
    @Operation(summary = "내가 진행 중인 미션 목록", description = "내가 진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MissionResponseDto.MissionPreViewListDTO> getMyChallengingMissions(@RequestParam(name = "page")  @ValidPage Integer page) {
        Long myUserId = 1L; // 임시 고정값 (추후 JWT에서 받아오도록 변경)

        // 1) 내가 CHALLENGING 상태로 진행 중인 Solve들 페이징 조회
        Page<Solve> solvePage = solveService.getSolvePageByUserIdAndStatus(myUserId, MissionStatus.CHALLENGING, page);

        // 2) Solve에서 미션 ID 리스트 추출
        List<Long> missionIds = solvePage.stream()
                .map(solve -> solve.getMission().getMissionId())
                .toList();

        // 3) mission 리스트 조회 (페이징 고려)
        List<Mission> missionList = missionService.getMissionsByIds(missionIds);

        // 4) missionList를 Page<Mission>으로 변환
        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> missionPage = new PageImpl<>(missionList, pageable, missionList.size());

        // 5) missionId -> Solve 맵 생성
        Map<Long, Solve> solveMap = solvePage.stream()
                .collect(Collectors.toMap(solve -> solve.getMission().getMissionId(), solve -> solve));

        return ApiResponse.onSuccess(StoreConverter.storeMissionListDTO(missionPage, solveMap));
    }

}
