package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class MissionReqDTO {

    public record GetMyMissions(
        @NotNull(message = "사용자 ID는 필수입니다.")
        @Positive(message = "사용자 ID는 양수여야 합니다.")
        Long memberId,

        @NotNull(message = "상태값은 필수입니다.")
        String status,          // IN_PROGRESS / COMPLETED

        @PositiveOrZero(message = "페이지는 0 이상이어야 합니다.")
        Integer page,

        @Positive(message = "사이즈는 양수여야 합니다.")
        Integer size
    ) {
        // 기본값 처리
        public Integer page() { return page == null ? 0 : page; }
        public Integer size() { return size == null ? 10 : size; }
    }
}