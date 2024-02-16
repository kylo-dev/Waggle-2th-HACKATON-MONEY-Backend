package com.waggle.moneyti.domain.MoneyTI.service;

import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import com.waggle.moneyti.domain.MoneyTI.dto.MoneyTIRequest;
import com.waggle.moneyti.domain.MoneyTI.repository.MoneyTIRepository;
import com.waggle.moneyti.domain.MoneyTIRecommend.MoneyTIRecommend;
import com.waggle.moneyti.domain.MoneyTIRecommend.repository.MoneyTIRecommendRepository;
import com.waggle.moneyti.global.exception.ApiException;
import com.waggle.moneyti.global.response.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MoneyTIService {
    
    private final MoneyTIRepository moneyTIRepository;
    private final MoneyTIRecommendRepository moneyTIRecommendRepository;
    
    public MoneyTI findMoneyTI(String moneyTI){
        return moneyTIRepository.findByName(moneyTI).orElseThrow(
                () -> new ApiException(ErrorStatus._NOT_FOUND_MONEYTI));
    }

    public String getMoneyTIResult(Integer[] request){
        List<MoneyTI> moneyTIList = moneyTIRepository.findAll();
        HashMap<String, Integer> score = new HashMap<>();
        moneyTIList.forEach(moneyTI -> score.put(moneyTI.getName(),0));

        for (int id = 0; id < request.length; id++) {
            if (request[id]==1) {
                moneyTIList = moneyTIRecommendRepository.findByRecommendId((long) id).stream().map(MoneyTIRecommend::getMoneyTI).toList();
                moneyTIList.forEach(moneyTI -> {
                    String key = moneyTI.getName();
                    score.replace(key, score.get(key) + 1);
                });
            }
        }
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        Map.Entry<String, Integer> maxEntry = Collections.max(score.entrySet(),comparator);

        return maxEntry.getKey();
    }
}
