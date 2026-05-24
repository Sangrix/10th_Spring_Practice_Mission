package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 미션 목록 조회 (진행중 / 완료) - MissionController
    @Query("SELECT mm FROM MemberMission mm " +
           "JOIN FETCH mm.mission m " +
           "JOIN FETCH m.store " +
           "WHERE mm.member.id = :memberId " +
           "AND mm.status = :status")
    Page<MemberMission> findByMemberIdAndStatus(
        @Param("memberId") Long memberId,
        @Param("status") String status,
        Pageable pageable
    );

    // 완료된 미션 수 조회 - StoreController (홈 화면)
    @Query("SELECT COUNT(mm) FROM MemberMission mm " +
           "WHERE mm.member.id = :memberId " +
           "AND mm.status = :status")
    int countByMemberIdAndStatus(
        @Param("memberId") Long memberId,
        @Param("status") String status
    );
}