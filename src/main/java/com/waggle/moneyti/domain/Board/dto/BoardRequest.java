package com.waggle.moneyti.domain.Board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "게시글 작성 DTO")
public class BoardRequest {

    @Schema(description = "MoneyTI 유형", example = "YOLO")
    private String moneyti;

    @Schema(description = "내용")
    private String content;
}
