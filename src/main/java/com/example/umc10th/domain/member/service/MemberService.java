package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberErrorCode;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp dto) {
        Member member = Member.builder()
            .name(dto.name())
            .gender(dto.gender())
            .birth(dto.birth())
            .address(dto.address())
            .build();

        Member saved = memberRepository.save(member);

        return MemberResDTO.SignUp.builder()
            .id(saved.getId())
            .createdAt(saved.getCreatedAt())
            .build();
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MyPage getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));
            // ↑ IllegalArgumentException 대신 ProjectException 사용
            //   → GeneralExceptionAdvice가 자동으로 잡아서 ApiResponse로 변환

        return MemberResDTO.MyPage.builder()
            .name(member.getName())
            .point(member.getPoint())
            .build();
    }
}
