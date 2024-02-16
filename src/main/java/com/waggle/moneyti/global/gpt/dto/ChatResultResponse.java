package com.waggle.moneyti.global.gpt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "gpt 추천 결과 DTO")
public class ChatResultResponse {
    @Schema(description = "추천 소비 습관, 2문장으로 주어집니다.")
    private String recommendHabit;
    @Schema(description = "추천 투자 상품, 2문장으로 주어집니다.")
    private String recommendProduct;
}
