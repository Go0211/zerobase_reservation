package com.zerobase.zerobase_reservation_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "content")
    String content;

    @Column(name = "create_time")
    LocalDateTime createTime;

    @Column(name = "modify_time")
    LocalDateTime modifyTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    Store store;
}
