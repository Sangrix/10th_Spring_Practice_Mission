package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.dto.StoreResDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class StoreConverter {

    // Mission Entity → MissionItem ResDTO
    public static StoreResDTO.MissionItem toMissionItem(Mission m) {
        int dDay = (int) ChronoUnit.DAYS.between(
            LocalDate.now(),
            m.getFinishedTime()
        );
        return StoreResDTO.MissionItem.builder()
            .missionId(m.getId())
            .storeName(m.getStore().getName())
            .storeCategory(m.getStore().getCategory().name())
            .missionContent(m.getContent())
            .rewardPoint(m.getPoint())
            .dDay(dDay)
            .build();
    }

    // 홈 화면 전체 → Home ResDTO
    public static StoreResDTO.Home toHomeRes(
        Member member,
        List<StoreResDTO.MissionItem> items,
        int completedCount,
        int currentPage,
        int totalPages,
        boolean hasNext
    ) {
        return StoreResDTO.Home.builder()
            .nickname(member.getName())
            .regionName(member.getRegion().getName())
            .point(member.getPoint())
            .completedMissionCount(completedCount)
            .totalMissionGoal(10)
            .rewardPoint(1000)
            .missions(items)
            .currentPage(currentPage)
            .totalPages(totalPages)
            .hasNext(hasNext)
            .build();
    }
}
