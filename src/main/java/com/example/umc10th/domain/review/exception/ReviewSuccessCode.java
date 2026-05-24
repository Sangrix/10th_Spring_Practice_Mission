package com.example.umc10th.domain.review.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
   CREATE(HttpStatus.CREATED,
       "REVIEW201_1",
       "성공적으로 리뷰를 달았습니다."),
   ;

   private final HttpStatus status;
   private final String code;
   private final String message;
}
