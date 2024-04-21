package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.dto.UsersDto;
import com.zerobase.zerobase_reservation_project.security.UsersDetail;
import com.zerobase.zerobase_reservation_project.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/info")
    public String userInfo(Model model, Principal principal) {
        UsersDto.Response response = usersService.getUsersData(principal.getName());

        model.addAttribute("usersData", response);

        return "main";
    }
}
