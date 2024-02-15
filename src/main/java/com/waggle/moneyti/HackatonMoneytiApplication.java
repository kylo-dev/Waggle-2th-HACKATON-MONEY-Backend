package com.waggle.moneyti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HackatonMoneytiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HackatonMoneytiApplication.class, args);
    }

}
