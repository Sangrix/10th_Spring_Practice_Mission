package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberErrorCode;
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
        Member member = MemberConverter.toMember(dto);
        Member saved = memberRepository.save(member);
        return MemberConverter.toSignUpRes(saved);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MyPage getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toMyPageRes(member);
    }
}