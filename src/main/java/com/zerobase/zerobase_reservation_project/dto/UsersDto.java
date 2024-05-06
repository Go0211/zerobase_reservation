package com.zerobase.zerobase_reservation_project.dto;

import com.zerobase.zerobase_reservation_project.entity.Users;
import com.zerobase.zerobase_reservation_project.type.Partner;
import com.zerobase.zerobase_reservation_project.type.UsersRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

public class UsersDto{

    @Getter
    @Setter
    @ParameterObject
    public static class Request {
        private String email;
        private String password;
        private String name;
        private String userRole;
        private String partner;

        public static Users toEntity(Request userRequest) {
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

        private static Partner initPartner(Request userRequest) {
            return userRequest.getUserRole().toUpperCase().equals("USER")
                    ? Partner.NOT_MANAGER : Partner.NO;
        }
    }

    @Getter
    @Setter
    @ParameterObject
    @Builder
    public static class Response {
        private String email;
        private String name;
        private String userRole;
        private String partner;

        public static UsersDto.Response toDto(Users users) {
            return Response.builder()
                    .email(users.getEmail())
                    .name(users.getName())
                    .userRole(users.getUsersRole().getText())
                    .partner(users.getPartner().getText())
                    .build();
        }
    }
}
