package com.zerobase.zerobase_reservation_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
public class ZerobaseReservationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZerobaseReservationProjectApplication.class, args);
    }

}
