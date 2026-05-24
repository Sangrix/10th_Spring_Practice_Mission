package com.example.umc10th.domain.ticket.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TicketSuccessCode implements BaseSuccessCode {

    CREATE_TICKET(HttpStatus.CREATED,
        "TICKET201_1",
        "문의가 등록되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
