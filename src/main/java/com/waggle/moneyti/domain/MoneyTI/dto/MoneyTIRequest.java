package com.waggle.moneyti.domain.MoneyTI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "MoneyTI 설문지 DTO")
public class MoneyTIRequest {
    @Schema(description = "10개의 설문에 대한 응답을 작성합니다. YES -> 1, NO -> 0")
    Integer[] request = new Integer[10];
}
