package com.waggle.moneyti.domain.MoneyTI.service;

import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import com.waggle.moneyti.domain.MoneyTI.repository.MoneyTIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MoneyTIService {
    
    private final MoneyTIRepository moneyTIRepository;
    
    public MoneyTI findMoneyTI(String moneyTI){
        return moneyTIRepository.findByName(moneyTI).orElseThrow();
    }

}
