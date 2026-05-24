package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

   @JsonPropertyOrder("isSuccess")
   private final Boolean isSuccess;

   @JsonPropertyOrder("code")
   private final String code;

   @JsonPropertyOrder("message")
   private final String message;

   @JsonPropertyOrder("result")
   private T result;

   // success (include result)

   // fail (include result)
   public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result){
      return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
   }
}
