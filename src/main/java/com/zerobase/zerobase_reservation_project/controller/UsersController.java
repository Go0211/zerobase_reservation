package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.dto.UsersDto;
import com.zerobase.zerobase_reservation_project.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/join-user")
    public String joinUser() {
        return "users/join-user";
    }

    @GetMapping("/join-manager")
    public String joinManager() {
        return "users/join-manager";
    }

    @PostMapping("/join")
    public String joinUser(
            @ModelAttribute("response")UsersDto.Response response
    ) {
        boolean saveState = usersService.join(response);

        if (saveState) {
            return "redirect:/users/login";
        } else {
//          에러메세지 출력
            return "error";
        }
    }

    @GetMapping("/info")
    public String userInfo(Model model) {
        return "main";
    }
}
