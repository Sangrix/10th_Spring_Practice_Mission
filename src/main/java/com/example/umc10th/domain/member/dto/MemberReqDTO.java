package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {

   public record GetInfo(Long id){}

   public record SignUp(String name, Gender gender, LocalDate birth, String address){}
}
