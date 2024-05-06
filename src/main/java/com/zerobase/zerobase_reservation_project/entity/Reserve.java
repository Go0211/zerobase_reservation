package com.zerobase.zerobase_reservation_project.entity;

import com.zerobase.zerobase_reservation_project.type.UseReserve;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserve")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserve {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "reserve_time")
    LocalDateTime reserveTime;

    @Column(name = "use")
    @Enumerated(EnumType.STRING)
    UseReserve useReserve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    Store store;
}
