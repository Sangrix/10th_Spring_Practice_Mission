package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Review review = Review.builder()
            .member(member)
            .store(store)
            .score(dto.score())
            .content(dto.content())
            .build();

        Review saved = reviewRepository.save(review);

        return ReviewResDTO.Create.builder()
            .reviewId(saved.getId())
            .storeName(store.getName())
            .rating(saved.getScore())
            .content(saved.getContent())
            .createdAt(saved.getCreatedAt())
            .build();
    }
}