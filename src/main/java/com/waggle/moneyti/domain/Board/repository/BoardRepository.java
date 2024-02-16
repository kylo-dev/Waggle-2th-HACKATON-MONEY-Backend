package com.waggle.moneyti.domain.Board.repository;

import com.waggle.moneyti.domain.Board.Board;
import com.waggle.moneyti.domain.enums.MoneyTI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByMoneyTI(MoneyTI moneyTI);


}
