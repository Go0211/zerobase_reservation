package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.dto.UsersDto;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.security.UsersDetail;
import com.zerobase.zerobase_reservation_project.service.StoreService;
import com.zerobase.zerobase_reservation_project.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UsersService usersService;
    private final StoreService storeService;

    @RequestMapping("/")
    public String main(Model model, Authentication authentication,
                       @RequestParam(value = "category", required = false) String category,
                       @RequestParam(value = "text", required = false) String text) {
        UsersDto.Response response = usersService.getUsersData(authentication.getName());
        List<Store> storeList = storeService.getStoreList(authentication.getName());
        List<Store> searchStoreList;
        if (text == null || text.equals("")) {
            searchStoreList = storeService.getAllStoreList();
        } else {
            searchStoreList = storeService.getSearchStoreList(category, text);
        }

        model.addAttribute("usersData", response);
        model.addAttribute("storeList", storeList);
        model.addAttribute("searchStoreList", searchStoreList);

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
