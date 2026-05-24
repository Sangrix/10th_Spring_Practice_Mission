package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    // MemberMission Entity → MissionItem ResDTO
    public static MissionResDTO.MissionItem toMissionItem(MemberMission mm) {
        int dDay = (int) ChronoUnit.DAYS.between(
            LocalDate.now(),
            mm.getMission().getFinishedTime()
        );
        return MissionResDTO.MissionItem.builder()
            .userMissionId(mm.getId())
            .missionId(mm.getMission().getId())
            .storeName(mm.getMission().getStore().getName())
            .missionContent(mm.getMission().getContent())
            .rewardPoint(mm.getMission().getPoint())
            .status(mm.getStatus().name())
            .dDay(dDay)
            .build();
    }

    // Page<MemberMission> → MissionList ResDTO
    public static MissionResDTO.MissionList toMissionList(
        List<MissionResDTO.MissionItem> items,
        int currentPage,
        int totalPages,
        boolean hasNext
    ) {
        return MissionResDTO.MissionList.builder()
            .missions(items)
            .currentPage(currentPage)
            .totalPages(totalPages)
            .hasNext(hasNext)
            .build();
    }
}