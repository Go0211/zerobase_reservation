package com.zerobase.zerobase_reservation_project.controller;

import com.zerobase.zerobase_reservation_project.entity.Reserve;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.service.ReserveService;
import com.zerobase.zerobase_reservation_project.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {
    private final ReserveService reserveService;
    private final StoreService storeService;


    @GetMapping("/")
    public String storeReserve(Model model, @RequestParam("id") Long id) {
        List<LocalTime> reserveList = reserveService.getStoreReserveList(id);

        model.addAttribute("storeId", id);
        model.addAttribute("reserveList", reserveList);

        return "/store/reserve";
    }

    @PostMapping("/")
    public String storeReserve(Model model, @RequestParam("id") Long id,
                               Authentication authentication,
                               @RequestParam("localTime") String localTime) {
        LocalTime localTimes = LocalTime.parse(localTime, DateTimeFormatter.ofPattern("HH:mm"));

        reserveService.insertStoreReserve(id,
                LocalDateTime.of(LocalDate.now(), localTimes),
                authentication.getName());

        return "redirect:/store/reserve-list";
    }

    @GetMapping("/reserve-list")
    public String storeReserveList(Model model, Authentication authentication) {

        List<Reserve> reserveList = reserveService.getMyReserveList(authentication.getName());
        model.addAttribute("reserveList", reserveList);

        return "/store/reserve-list";
    }

    @GetMapping("/invite/{id}")
    public String inviteReserve(Model model, @PathVariable("id")Long id) {
        model.addAttribute("store", storeService.getStore(id));

        return "/reserve/invite";
    }

    @GetMapping("/invite/{id}/{reserveId}")
    public String inviteReserve(Model model,
                                @PathVariable("id")Long id,
                                @PathVariable("reserveId")Long reserveId) {
        reserveService.useReserve(reserveId);

        return "redirect:/invite/"+id;
    }
}
