package com.example.umc10th.domain.ticket.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.ticket.enums.Category;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ticket_id")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "member_id", nullable = false)
   private Member member;

   @Column(name = "title", nullable = false)
   private String title;

   @Enumerated(EnumType.STRING)
   @Column(name = "category", nullable = false)
   private Category category;

   @Column(name = "content")
   private String content;

   @Enumerated(EnumType.STRING)
   @Column(name = "is_answered", nullable = false)
   private TicketReply isAnswered;
}
