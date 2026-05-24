package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record Create(
        Long reviewId,
        String storeName,
        Integer rating,
        String content,
        LocalDateTime createdAt
    ) {}

    // 커서 기반 리뷰 목록 추가
    @Builder
    public record ReviewList(
        List<ReviewItem> reviews,
        Long nextCursorId,      // 다음 요청 시 사용할 커서
        Double nextCursorScore,
        Boolean hasNext
    ) {}

    @Builder
    public record ReviewItem(
        Long reviewId,
        String storeName,
        String nickname,
        Double score,
        String content,
        LocalDateTime createdAt
    ) {}
}