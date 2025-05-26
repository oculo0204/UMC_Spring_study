package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.service.MissionService.MissionService;
import umc.spring.service.StoreService.StoreService;
import umc.spring.web.dto.store.AddMissionInStoreRequestDto;
import umc.spring.web.dto.store.StoreRequestDto;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;
    @PostMapping("/{storeId}")
    public ResponseEntity<?> addMission(@Valid @RequestBody AddMissionInStoreRequestDto.addDto requestDto) {
        Mission newMission = missionService.addMissionInStore(requestDto);
        return ResponseEntity.ok(ApiResponse.onSuccess(MissionConverter.toResultDTO(newMission)));
    }
}
