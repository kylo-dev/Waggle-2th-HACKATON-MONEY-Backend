package com.waggle.moneyti.domain.MoneyTI.dto;

import com.waggle.moneyti.global.gpt.dto.ChatResultResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "MoneyTI 검사 결과 및 gpt 추천 정보 DTO")
public class MoneyTIResponse {

    @Schema(description = "MoneyTI 검사 결과", example = "BTS")
    private String moneyTI;

    @Schema(description = "MoneyTI ChatGPT 추천 데이터")
    private ChatResultResponse gptRecommend;
}
