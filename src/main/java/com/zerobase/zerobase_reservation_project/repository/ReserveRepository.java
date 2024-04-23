package com.zerobase.zerobase_reservation_project.repository;

import com.zerobase.zerobase_reservation_project.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
