package com.waggle.moneyti.domain.Board.repository;

import com.waggle.moneyti.domain.Board.Board;
import com.waggle.moneyti.domain.enums.MoneyTI;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByMoneyTI(MoneyTI moneyTI);

    List<Board> findAllByMoneyTI(MoneyTI moneyTI, PageRequest pageRequest);
}
