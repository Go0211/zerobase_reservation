package com.zerobase.zerobase_reservation_project.entity;

import com.zerobase.zerobase_reservation_project.type.Partner;
import com.zerobase.zerobase_reservation_project.type.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "password")
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    UserRole userRole;

    @Column(name = "partner")
    @Enumerated(value = EnumType.STRING)
    Partner partner;
}
