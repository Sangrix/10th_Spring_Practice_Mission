package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            .map(MissionConverter::toMissionItem)
            .toList();

        return MissionConverter.toMissionList(
            items,
            memberMissions.getNumber(),
            memberMissions.getTotalPages(),
            memberMissions.hasNext()
        );
    }
}