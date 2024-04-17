package com.zerobase.zerobase_reservation_project.entity;

import com.zerobase.zerobase_reservation_project.type.UseReserve;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "reserve_time")
    LocalDateTime reserveTime;

    @Column(name = "request_time")
    LocalDateTime requestTime;

    @Column(name = "confirm_reserve_time")
    LocalDateTime confirmTime;

    @Column(name = "use")
    UseReserve useReserve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    Store store;
}
