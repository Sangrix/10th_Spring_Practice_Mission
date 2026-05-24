package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 오프셋 기반 페이지네이션
    @GetMapping("/missions/me")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
        @RequestBody @Valid MissionReqDTO.GetMyMissions dto
    ) {
        return ApiResponse.onSuccess(
            MissionSuccessCode.GET_MY_MISSION,
            missionService.getMyMissions(
                dto.memberId(),
                dto.status(),
                dto.page(),
                dto.size()
            )
        );
    }
}