package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.exception.StoreSuccessCode;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/stores/home")
    public ApiResponse<StoreResDTO.Home> getHome(
        @RequestParam Long memberId,                        // 추후 JWT로 교체
        @RequestParam(defaultValue = "0")  Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
            StoreSuccessCode.GET_HOME,
            storeService.getHome(memberId, page, size)
        );
    }
}