package com.waggle.moneyti.domain.MoneyTI.repository;

import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoneyTIRepository extends JpaRepository<MoneyTI, Long> {

    Optional<MoneyTI> findByName(String name);

}
