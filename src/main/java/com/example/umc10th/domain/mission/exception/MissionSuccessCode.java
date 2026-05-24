package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

   GET_MISSION(HttpStatus.OK,
       "MISSION200_1",
       "미션을 조회했습니다."),
   ;

   private final HttpStatus status;
   private final String code;
   private final String message;
}
