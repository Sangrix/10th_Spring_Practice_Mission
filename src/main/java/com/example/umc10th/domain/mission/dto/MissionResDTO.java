package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.IsActive;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

   @Builder
    public record MissionList(
        List<MissionItem> missions,
        Integer currentPage,
        Integer totalPages,
        Boolean hasNext
    ) {}

    @Builder
    public record MissionItem(
        Long userMissionId,       // member_mission_id
        Long missionId,
        String storeName,
        String missionContent,
        Integer rewardPoint,
        String status,            // IN_PROGRESS / COMPLETED
        Integer dDay
    ) {}
}
