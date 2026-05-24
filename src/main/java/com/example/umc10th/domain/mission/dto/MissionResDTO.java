package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.IsActive;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

   @Builder
   public record GetMission(List<MissionItem> missions){}

   @Builder
   public record MissionItem(Long userId, Long missionId, String name, String content, IsActive isActive, LocalDateTime finishedTime){}
}
