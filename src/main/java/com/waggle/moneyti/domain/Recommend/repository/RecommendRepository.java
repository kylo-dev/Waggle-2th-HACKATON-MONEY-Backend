package com.waggle.moneyti.domain.Recommend.repository;

import com.waggle.moneyti.domain.Recommend.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {

}
