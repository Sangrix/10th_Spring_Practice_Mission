package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.Create createReview(ReviewReqDTO.Create dto) {
        Member member = memberRepository.findById(dto.memberId())
            .orElseThrow(() -> new ProjectException(ReviewErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(dto.storeId())
            .orElseThrow(() -> new ProjectException(ReviewErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(dto, member, store);
        Review saved = reviewRepository.save(review);
        return ReviewConverter.toCreateRes(saved);
    }

    @Transactional(readOnly = true)
    public ReviewResDTO.ReviewList getMyReviews(ReviewReqDTO.GetMyReviews dto) {
        memberRepository.findById(dto.memberId())
            .orElseThrow(() -> new ProjectException(ReviewErrorCode.MEMBER_NOT_FOUND));

        // size+1 개 조회해서 다음 페이지 여부 확인
        PageRequest pageRequest = PageRequest.of(0, dto.size() + 1);

        List<Review> reviews;

        if ("SCORE".equalsIgnoreCase(dto.sortBy())) {
            reviews = reviewRepository.findByMemberIdOrderByScore(
                dto.memberId(),
                dto.cursorScore(),
                dto.cursorId(),
                pageRequest
            );
        } else {
            // 기본값: ID 순
            reviews = reviewRepository.findByMemberIdOrderById(
                dto.memberId(),
                dto.cursorId(),
                pageRequest
            );
        }

        return ReviewConverter.toReviewList(reviews, dto.size());
    }
}