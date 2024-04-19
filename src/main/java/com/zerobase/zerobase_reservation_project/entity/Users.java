package com.zerobase.zerobase_reservation_project.entity;

import com.zerobase.zerobase_reservation_project.type.Partner;
import com.zerobase.zerobase_reservation_project.type.UsersRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email" ,unique = true)
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    UsersRole usersRole;

    @Column(name = "partner")
    @Enumerated(value = EnumType.STRING)
    Partner partner;
}
