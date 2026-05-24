package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 커서 기반
    @Query("SELECT r FROM Review r " +
           "JOIN FETCH r.store " +
           "WHERE r.member.id = :memberId " +
           "AND (:cursorId IS NULL OR r.id < :cursorId) " +
           "ORDER BY r.id DESC")
    List<Review> findByMemberIdOrderById(
        @Param("memberId") Long memberId,
        @Param("cursorId") Long cursorId,
        Pageable pageable
    );

    // 별점 순 커서 기반
    @Query("SELECT r FROM Review r " +
           "JOIN FETCH r.store " +
           "WHERE r.member.id = :memberId " +
           "AND (:cursorScore IS NULL OR r.score < :cursorScore " +
           "     OR (r.score = :cursorScore AND r.id < :cursorId)) " +
           "ORDER BY r.score DESC, r.id DESC")
    List<Review> findByMemberIdOrderByScore(
        @Param("memberId") Long memberId,
        @Param("cursorScore") Double cursorScore,
        @Param("cursorId") Long cursorId,
        Pageable pageable
    );
}