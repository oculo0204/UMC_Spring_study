package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionService;
import umc.spring.service.MissionService.SolveService;
import umc.spring.web.dto.mission.CreateSolveRequestDto;
import umc.spring.web.dto.mission.SolveResponseDto;
import umc.spring.web.dto.store.AddMissionInStoreRequestDto;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;
    private final SolveService solveService;

    @PostMapping("/{storeId}")
    public ResponseEntity<?> addMission(@Valid @RequestBody AddMissionInStoreRequestDto.addDto requestDto) {
        Mission newMission = missionService.addMissionInStore(requestDto);
        return ResponseEntity.ok(ApiResponse.onSuccess(MissionConverter.toResultDTO(newMission)));
    }

    @PostMapping("/challenge/{missionId}")
    public ResponseEntity<?> createSolve(@PathVariable Long missionId, @Valid @RequestBody CreateSolveRequestDto requestDto) {
        SolveResponseDto solveResponse = solveService.createSolve(missionId, requestDto);
        return ResponseEntity.ok(ApiResponse.onSuccess(solveResponse));
    }

}
