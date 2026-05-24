package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.Create dto, Member member, Store store) {
        return Review.builder()
            .member(member)
            .store(store)
            .score(dto.rating())
            .content(dto.content())
            .build();
    }

    public static ReviewResDTO.Create toCreateRes(Review review) {
        return ReviewResDTO.Create.builder()
            .reviewId(review.getId())
            .storeName(review.getStore().getName())
            .rating(review.getScore())
            .content(review.getContent())
            .createdAt(review.getCreatedAt())
            .build();
    }

    public static ReviewResDTO.ReviewItem toReviewItem(Review review) {
        return ReviewResDTO.ReviewItem.builder()
            .reviewId(review.getId())
            .storeName(review.getStore().getName())
            .nickname(review.getMember().getName())
            .score((double) review.getScore())
            .content(review.getContent())
            .createdAt(review.getCreatedAt())
            .build();
    }

    public static ReviewResDTO.ReviewList toReviewList(
        List<Review> reviews,
        int size
    ) {
        boolean hasNext = reviews.size() > size;

        // 다음 페이지 여부 확인용으로 size+1 개 조회했으므로 초과분 제거
        List<Review> content = hasNext ? reviews.subList(0, size) : reviews;

        Long nextCursorId = hasNext ? content.get(content.size() - 1).getId() : null;
        Double nextCursorScore = hasNext
            ? (double) content.get(content.size() - 1).getScore()
            : null;

        return ReviewResDTO.ReviewList.builder()
            .reviews(content.stream().map(ReviewConverter::toReviewItem).toList())
            .nextCursorId(nextCursorId)
            .nextCursorScore(nextCursorScore)
            .hasNext(hasNext)
            .build();
    }
}