package com.zerobase.zerobase_reservation_project.repository;

import com.zerobase.zerobase_reservation_project.entity.Reserve;
import com.zerobase.zerobase_reservation_project.entity.Store;
import com.zerobase.zerobase_reservation_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {
    List<Reserve> findAllByStore(Store store);

    List<Reserve> findAllByUsers(Users users);
}
