package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.IsActive;
import com.example.umc10th.domain.store.entity.Region;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "mission")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mission extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "mission_id")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "store_id", nullable = false)
   private Store store;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "region_id", nullable = false)
   private Region region;

   @Column(name = "content", nullable = false)
   private String content;

   @Column(name = "point", nullable = false)
   private Integer point;

   @Enumerated(EnumType.STRING)
   @Column(name = "active", nullable = false)
   private IsActive isActive;

   @Column(name = "started_time", nullable = false)
   private LocalDate startedTime;

   @Column(name = "finished_time", nullable = false)
   private LocalDate finishedTime;
}
