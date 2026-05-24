package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

   private final MemberService memberService;

   @PostMapping("/auth/signup")
   public ApiResponse<MemberResDTO.SignUp> signup(@RequestBody MemberReqDTO.SignUp dto) {
      BaseSuccessCode code = MemberSuccessCode.SIGN_UP;
      return ApiResponse.onSuccess(code, memberService.signUp(dto));
   }

   @GetMapping("/members/me")
   public ApiResponse<MemberResDTO.MyPage> getMyPage(
       @RequestParam Long id   // 추후 JWT 적용 시 @AuthenticationPrincipal로 교체
   ) {
      return ApiResponse.onSuccess(
          MemberSuccessCode.GET_MY_PAGE,
          memberService.getMyPage(id)
      );
   }
}
