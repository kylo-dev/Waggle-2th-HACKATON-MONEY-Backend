package com.waggle.moneyti.domain.MoneyTIRecommend.repository;

import com.waggle.moneyti.domain.MoneyTIRecommend.MoneyTIRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoneyTIRecommendRepository extends JpaRepository<MoneyTIRecommend,Long> {
    List<MoneyTIRecommend> findByRecommendId(Long recommendId);
}
