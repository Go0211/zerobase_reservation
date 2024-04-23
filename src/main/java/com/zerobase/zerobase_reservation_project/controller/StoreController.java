package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.dto.StoreDto;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

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

    @GetMapping ("/delete")
    public String storeDelete(@RequestParam("id") Long id) {
        storeService.deleteStore(id);

        return "redirect:/";
    }

    @GetMapping("/detail")
    public String storeDetail(Model model, @RequestParam("id") Long id) {
        Store store = storeService.getStore(id);

        model.addAttribute("store", store);

        return "store/detail";
    }

    @GetMapping("/reserve")
    public String storeReserve(Model model, @RequestParam("id") Long id) {
        Store store = storeService.getStore(id);

        model.addAttribute("store", store);

        return "/store/reserve";
    }

    @PostMapping("/reserve")
    public String storeReserve(Model model, @RequestParam("id") Long id,
                               Authentication authentication,
                               @RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                               LocalDateTime datetime) {
        storeService.insertStoreReserve(id, datetime, authentication.getName());

        return "redirect:/reserve-list";
    }

    @GetMapping("/reserve-list")
    public String storeReserveList(Model model, @RequestParam("id") Long id) {
        return "/store/reserve-list";
    }
}
