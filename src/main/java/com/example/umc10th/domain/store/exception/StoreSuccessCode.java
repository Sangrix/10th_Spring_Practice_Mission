package com.example.umc10th.domain.store.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    GET_HOME(HttpStatus.OK,
        "STORE200_1",
        "홈 화면이 조회되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
