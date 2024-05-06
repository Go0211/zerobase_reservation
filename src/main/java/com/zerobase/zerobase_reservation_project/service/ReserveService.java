package com.zerobase.zerobase_reservation_project.service;

import com.zerobase.zerobase_reservation_project.entity.Reserve;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.entity.Users;
import com.zerobase.zerobase_reservation_project.repository.ReserveRepository;
import com.zerobase.zerobase_reservation_project.repository.StoreRepository;
import com.zerobase.zerobase_reservation_project.repository.UsersRepository;
import com.zerobase.zerobase_reservation_project.type.UseReserve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final StoreRepository storeRepository;
    private final ReserveRepository reserveRepository;
    private final UsersRepository usersRepository;

    public void insertStoreReserve(Long id, LocalDateTime datetime, String email) {
        reserveRepository.save(Reserve.builder()
                .reserveTime(datetime)
                .useReserve(UseReserve.NOT_USE)
                .users(usersRepository.findByEmail(email).orElseThrow(
                        () -> new IllegalArgumentException("not have email")
                ))
                .store(storeRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("not have store")
                ))
                .build());
    }

    public List<LocalTime> getStoreReserveList(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("not have store")
        );

        return getLocalTimeList(store);
    }

    private List<LocalTime> getLocalTimeList(Store store) {
        List<LocalTime> ldtList = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            ldtList.add(LocalTime.of(i, 0));
            ldtList.add(LocalTime.of(i, 30));
        }

        for (Reserve reserve : reserveRepository.findAllByStore(store)) {
            LocalTime reservedTime = LocalTime.of(
                    reserve.getReserveTime().getHour(),
                    reserve.getReserveTime().getMinute()
            );

            ldtList.remove(reservedTime);
        }
        return ldtList;
    }

    public List<Reserve> getMyReserveList(String email) {
        Users users = usersRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("not have users")
        );

        return reserveRepository.findAllByUsers(users);
    }

    public void useReserve(Long reserveId) {
        Reserve reserve = reserveRepository.findById(reserveId).orElseThrow(
                () -> new IllegalArgumentException("not have reserve")
        );

        reserve.setUseReserve(UseReserve.USE);

        reserveRepository.save(reserve);
    }
}
