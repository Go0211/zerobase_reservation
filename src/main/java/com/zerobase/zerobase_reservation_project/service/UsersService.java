package com.zerobase.zerobase_reservation_project.service;

import com.zerobase.zerobase_reservation_project.dto.UsersDto;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.entity.Users;
import com.zerobase.zerobase_reservation_project.repository.UsersRepository;
import com.zerobase.zerobase_reservation_project.type.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optUsers = usersRepository.findByEmail(username);

        if (optUsers.isPresent()) {
            Users users = optUsers.get();

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + users.getUsersRole().getText()));

            return new User(users.getEmail(), users.getPassword(), authorities);
        }

        return null;

    }

    @Transactional
    public boolean join(UsersDto.Request response) {
        response.setPassword(passwordEncoder.encode(response.getPassword()));
        Users users = UsersDto.Request.toEntity(response);

        if (usersRepository.findByEmail(users.getEmail()).isPresent()) {
            return false;
        }

        usersRepository.save(users);
        return true;
    }

    @Transactional
    public UsersDto.Response getUsersData(String email) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not hava user"));

        return UsersDto.Response.toDto(users);
    }

    public void joinPartner(String email) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not have user"));

        users.setPartner(Partner.YES);
        usersRepository.save(users);
    }
}
