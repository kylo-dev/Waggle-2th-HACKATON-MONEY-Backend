package com.waggle.moneyti.domain.MoneyTI.controller;

import com.waggle.moneyti.domain.MoneyTI.dto.MoneyTIRequest;
import com.waggle.moneyti.domain.MoneyTI.dto.MoneyTIResponse;
import com.waggle.moneyti.domain.MoneyTI.service.MoneyTIService;
import com.waggle.moneyti.global.gpt.service.PromptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/money-ti")
public class MoneyTIController {

    private final PromptService promptService;
    private final MoneyTIService moneyTIService;

    @PostMapping
    @Operation(summary = "MoneyTI 검사 결과 및 gpt 추천 정보 조회 API", description = "MoneyTI 검사 결과와 그에 맞는 gpt 추천 정보를 반환합니다.")
    public MoneyTIResponse getMoneyTIResult(@RequestBody MoneyTIRequest moneyTIRequest) throws ParseException {
        String moneyTI = moneyTIService.getMoneyTIResult(moneyTIRequest.getRequest());
        return new MoneyTIResponse(moneyTI, promptService.prompt(moneyTI));
    }
}
