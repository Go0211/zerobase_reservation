package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.dto.UsersDto;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.security.UsersDetail;
import com.zerobase.zerobase_reservation_project.service.StoreService;
import com.zerobase.zerobase_reservation_project.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UsersService usersService;
    private final StoreService storeService;

    @GetMapping("/")
    public String main(Model model, Principal principal) {
        UsersDto.Response response = usersService.getUsersData(principal.getName());
        List<Store> storeList = storeService.getStoreList(principal.getName());

        model.addAttribute("usersData", response);
        model.addAttribute("storeList", storeList);

        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/join-user")
    public String joinUser() {
        return "login/join-user";
    }

    @GetMapping("/join-manager")
    public String joinManager() {
        return "login/join-manager";
    }

    @PostMapping("/join")
    public String joinUser(
            @ModelAttribute("response") UsersDto.Request response
    ) {
        boolean saveState = usersService.join(response);

        if (saveState) {
            return "redirect:/login";
        } else {
//          에러메세지 출력
            return "error";
        }
    }

    @GetMapping("/join-partner")
    public String joinPartner(Principal principal) {
        usersService.joinPartner(principal.getName());
        return "redirect:/";
    }
}
