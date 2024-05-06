package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.dto.UsersDto;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.entity.Users;
import com.zerobase.zerobase_reservation_project.service.StoreService;
import com.zerobase.zerobase_reservation_project.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Login", description = "Login apis")
public class LoginController {
    private final UsersService usersService;

    @GetMapping("/login")
    public String login() {
        return "/login/login";
    }

    @GetMapping("/join-user")
    public String joinUser() {
        return "/login/join-user";
    }

    @GetMapping("/join-manager")
    public String joinManager() {
        return "/login/join-manager";
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinUser(
            @ModelAttribute("response") UsersDto.Request response
    ) {
        Users users = usersService.join(response);

        return ResponseEntity.ofNullable(users);
    }

    @GetMapping("/join-partner")
    @Operation(summary = "파트너쉽 설정")
    public ResponseEntity<?> joinPartner(Principal principal) {
        return ResponseEntity.ok(usersService.joinPartner(principal.getName()));
    }
}
