package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/missions/me")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
        @RequestParam Long memberId,        // 추후 JWT로 교체
        @RequestParam String status,        // IN_PROGRESS / COMPLETED
        @RequestParam(defaultValue = "0")  Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
            MissionSuccessCode.GET_MY_MISSION,
            missionService.getMyMissions(memberId, status, page, size)
        );
    }
}
