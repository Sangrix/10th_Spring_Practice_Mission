package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {
   public record Create(
       Long memberId,
       Long storeId,
       Integer score,
       String content
   ){}
}