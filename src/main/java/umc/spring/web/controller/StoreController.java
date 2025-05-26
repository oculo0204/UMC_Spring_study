package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.service.StoreService.StoreService;
import umc.spring.web.dto.store.StoreRequestDto;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/join")
    public ResponseEntity<?> createStore(@Valid @RequestBody StoreRequestDto requestDto) {
        storeService.createStore(requestDto);
        return ResponseEntity.ok().build();
    }

}
