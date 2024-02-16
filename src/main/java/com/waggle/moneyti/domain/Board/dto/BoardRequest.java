package com.waggle.moneyti.domain.Board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "게시글 작성 DTO")
public class BoardRequest {

    @Schema(description = "MoneyTIType 유형", example = "BTS")
    private String moneyTI;

    @Schema(description = "내용")
    private String content;
}
