package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ReviewReqDTO {

    public record Create(
        @NotNull(message = "사용자 ID는 필수입니다.")
        @Positive(message = "사용자 ID는 양수여야 합니다.")
        Long memberId,

        @NotNull(message = "가게 ID는 필수입니다.")
        @Positive(message = "가게 ID는 양수여야 합니다.")
        Long storeId,

        @NotNull(message = "별점은 필수입니다.")
        Integer rating,

        @NotNull(message = "내용은 필수입니다.")
        String content
    ) {}

    public record GetMyReviews(
        @NotNull(message = "사용자 ID는 필수입니다.")
        @Positive(message = "사용자 ID는 양수여야 합니다.")
        Long memberId,

        // 커서 기반: 마지막으로 받은 reviewId 또는 score
        Long cursorId,          // ID 순 커서 (null이면 첫 페이지)
        Double cursorScore,     // 별점 순 커서 (null이면 첫 페이지)

        @NotNull(message = "정렬 기준은 필수입니다.")
        String sortBy,          // "ID" / "SCORE"

        @Positive(message = "사이즈는 양수여야 합니다.")
        Integer size
    ) {
        public Integer size() { return size == null ? 10 : size; }
    }
}