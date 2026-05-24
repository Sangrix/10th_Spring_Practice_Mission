package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ApiResponse<ReviewResDTO.Create> createReview(
        @RequestBody @Valid ReviewReqDTO.Create dto
    ) {
        return ApiResponse.onSuccess(
            ReviewSuccessCode.CREATE_REVIEW,
            reviewService.createReview(dto)
        );
    }

    @GetMapping("/reviews/me")
    public ApiResponse<ReviewResDTO.ReviewList> getMyReviews(
        @RequestBody @Valid ReviewReqDTO.GetMyReviews dto
    ) {
        return ApiResponse.onSuccess(
            ReviewSuccessCode.GET_MY_REVIEWS,
            reviewService.getMyReviews(dto)
        );
    }
}