package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreService;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.store.StoreRequestDto;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/join")
    public ResponseEntity<?> createStore(@Valid @RequestBody StoreRequestDto.JoinDto requestDto) {
        Store newStore = storeService.createStore(requestDto);
        return ResponseEntity.ok(ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(newStore)));
    }
    @PostMapping("/review")
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewRequestDto.makeDto requestDto){
        Review newReview = storeService.createReview(requestDto);
        return ResponseEntity.ok(ApiResponse.onSuccess(ReviewConverter.toMakeResultDTO(requestDto)));
    }

}
