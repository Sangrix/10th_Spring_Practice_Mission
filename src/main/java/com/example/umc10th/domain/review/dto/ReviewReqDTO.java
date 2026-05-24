package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {
   public record Create(Long storeId, Integer score, String Content, String imageUrl){}
}