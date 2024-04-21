package com.zerobase.zerobase_reservation_project.repository;

import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);

    List<Store> findAllByUsers(Users users);
}
