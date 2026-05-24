package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    // ReqDTO → Entity
    public static Member toMember(MemberReqDTO.SignUp dto) {
        return Member.builder()
            .name(dto.name())
            .gender(dto.gender())
            .birth(dto.birth())
            .address(dto.address())
            .build();
    }

    // Entity → ResDTO (회원가입)
    public static MemberResDTO.SignUp toSignUpRes(Member member) {
        return MemberResDTO.SignUp.builder()
            .id(member.getId())
            .createdAt(member.getCreatedAt())
            .build();
    }

    // Entity → ResDTO (마이페이지)
    public static MemberResDTO.MyPage toMyPageRes(Member member) {
        return MemberResDTO.MyPage.builder()
            .name(member.getName())
            .point(member.getPoint())
            .build();
    }
}