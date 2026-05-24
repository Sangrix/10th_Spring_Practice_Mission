package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.MissionList getMyMissions(
        Long memberId,
        String status,
        Integer page,
        Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<MemberMission> memberMissions =
            memberMissionRepository.findByMemberIdAndStatus(
                memberId, status, pageRequest
            );

        List<MissionResDTO.MissionItem> items = memberMissions.getContent()
            .stream()
            .map(mm -> {
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
            })
            .toList();

        return MissionResDTO.MissionList.builder()
            .missions(items)
            .currentPage(memberMissions.getNumber())
            .totalPages(memberMissions.getTotalPages())
            .hasNext(memberMissions.hasNext())
            .build();
    }
}
