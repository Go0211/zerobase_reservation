package com.zerobase.zerobase_reservation_project.dto;

import com.zerobase.zerobase_reservation_project.entity.Users;
import com.zerobase.zerobase_reservation_project.type.Partner;
import com.zerobase.zerobase_reservation_project.type.UsersRole;
import lombok.Getter;
import lombok.Setter;

public class UsersDto{

    @Getter
    @Setter
    public static class Response {
        private String email;
        private String password;
        private String name;
        private String userRole;
        private String partner;

        public static Users toEntity(UsersDto.Response userRequest) {
            return Users.builder()
                    .email(userRequest.email)
                    .password(userRequest.password)
                    .name(userRequest.getName())
                    .usersRole(UsersRole.fromString(userRequest.getUserRole()))
                    .partner(
                            initPartner(userRequest)
                    )
                    .build();
        }

        private static Partner initPartner(Response userRequest) {
            return userRequest.getUserRole().toUpperCase().equals("USER")
                    ? Partner.NOT_MANAGER : Partner.NO;
        }
    }
}
