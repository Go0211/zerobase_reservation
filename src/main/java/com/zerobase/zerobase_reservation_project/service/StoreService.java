package com.zerobase.zerobase_reservation_project.service;

import com.zerobase.zerobase_reservation_project.dto.StoreDto;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.entity.Users;
import com.zerobase.zerobase_reservation_project.repository.StoreRepository;
import com.zerobase.zerobase_reservation_project.repository.UsersRepository;
import com.zerobase.zerobase_reservation_project.type.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UsersRepository usersRepository;

    public boolean insertStore(StoreDto.Request request, String email) {
        Optional<Store> optStore = storeRepository
                .findByName(request.getName());
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not have user"));

        if (optStore.isPresent() || !users.getPartner().equals(Partner.YES)) {
//            에러던지기
            return false;
        }

        Store store = request.toEntity(request);
        store.setUsers(users);

        storeRepository.save(store);

        return true;
    }

    public List<Store> getStoreList(String email) {
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not hava user"));

        return storeRepository.findAllByUsers(users);
    }

    public void updateStore(Long id, StoreDto.Request request) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not have store"));

        store.setName(request.getName());
        store.setLocation(request.getLocation());
        store.setDescription(request.getDescription());

        storeRepository.save(store);
    }

    public Store getStore(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not have store"));
    }

    public void deleteStore(Long id) {
        storeRepository.delete(getStore(id));
    }
}
