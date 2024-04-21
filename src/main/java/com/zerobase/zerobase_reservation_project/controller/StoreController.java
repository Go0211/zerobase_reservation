package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.dto.StoreDto;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/register")
    public String storeRegister() {
        return "manager/insert-store";
    }

    @PostMapping("/register")
    public String storeRegister(@ModelAttribute("request") StoreDto.Request request, Principal principal) {
        if (!storeService.insertStore(request, principal.getName())) {
//          에러 던지기
        }

        return "redirect:/";
    }

    @GetMapping("/update")
    public String storeUpdate(Model model,
                              @RequestParam("id") Long id
    ) {
        model.addAttribute("store", storeService.getStore(id));

        return "manager/update-store";
    }

    @PostMapping("/update")
    public String storeUpdate(@RequestParam("id") Long id,
                              @ModelAttribute("request") StoreDto.Request request
    ) {
        storeService.updateStore(id, request);

        return "redirect:/";
    }
}
