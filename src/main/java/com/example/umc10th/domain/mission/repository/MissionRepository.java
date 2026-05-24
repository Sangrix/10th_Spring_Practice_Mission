package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 지역 기반 도전 가능한 미션 조회 (페이징) - StoreController (홈 화면)
    @Query("SELECT m FROM Mission m " +
           "JOIN FETCH m.store s " +
           "WHERE s.region.id = :regionId " +
           "AND m.isActive = 'TRUE'")
    Page<Mission> findActiveByRegionId(
        @Param("regionId") Long regionId,
        Pageable pageable
    );
}