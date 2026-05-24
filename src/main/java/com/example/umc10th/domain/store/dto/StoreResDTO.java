package com.example.umc10th.domain.store.dto;

import lombok.Builder;
import java.util.List;

public class StoreResDTO {

    @Builder
    public record Home(
        String nickname,
        String regionName,
        Long point,
        Integer completedMissionCount,
        Integer totalMissionGoal,
        Integer rewardPoint,
        List<MissionItem> missions,
        Integer currentPage,
        Integer totalPages,
        Boolean hasNext
    ) {}

    @Builder
    public record MissionItem(
        Long missionId,
        String storeName,
        String storeCategory,
        String missionContent,
        Integer rewardPoint,
        Integer dDay
    ) {}
}