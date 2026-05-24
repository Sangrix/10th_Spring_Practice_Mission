package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

   @Builder
   public record GetInfo(String name, Gender FEMALE, Integer point){}

   @Builder
   public record SignUp(Long id, LocalDateTime createdAt){}
}
