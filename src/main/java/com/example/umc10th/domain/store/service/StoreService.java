package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.dto.StoreResDTO;
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
public class StoreService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public StoreResDTO.Home getHome(Long memberId, Integer page, Integer size) {

        // 1. 멤버 조회
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 멤버의 선택 지역 기반 미션 목록 조회 (페이징)
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Mission> missionPage = missionRepository.findActiveByRegionId(
            member.getRegion().getId(), pageRequest
        );

        // 3. 완료된 미션 수 조회
        int completedCount = memberMissionRepository
            .countByMemberIdAndStatus(memberId, "COMPLETED");

        // 4. MissionItem 변환
        List<StoreResDTO.MissionItem> items = missionPage.getContent()
            .stream()
            .map(m -> {
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
            })
            .toList();

        return StoreResDTO.Home.builder()
            .nickname(member.getName())
            .regionName(member.getRegion().getName())
            .point(member.getPoint())
            .completedMissionCount(completedCount)
            .totalMissionGoal(10)
            .rewardPoint(1000)
            .missions(items)
            .currentPage(missionPage.getNumber())
            .totalPages(missionPage.getTotalPages())
            .hasNext(missionPage.hasNext())
            .build();
    }
}