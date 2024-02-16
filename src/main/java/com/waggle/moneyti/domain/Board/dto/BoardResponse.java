package com.waggle.moneyti.domain.Board.dto;

import com.waggle.moneyti.domain.Board.Board;
import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class BoardResponse {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "게시글 작성 결과 DTO")
    public static class BoardPost {

        @Schema(description = "게시글 Id")
        private Long id;

        @Schema(description = "게시글 생성 시간")
        private LocalDateTime createdAt;
    }

    public static BoardPost toBoardPost(Long id) {
        return BoardPost.builder()
            .id(id)
            .createdAt(LocalDateTime.now())
            .build();
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "게시글 목록 조회")
    public static class BoardList {

        @Schema(description = "게시글 내용")
        private String content;

        @Schema(description = "MoneyTI 유형")
        private String moneyTI;
    }

    public static BoardList toBoardList(Board board) {

        return BoardList.builder()
            .content(board.getContent())
                .moneyTI(board.getMoneyTI().getName())
            .build();
    }


    public static Board toEntity(MoneyTI moneyTI, String content) {
        return Board.builder()
            .content(content)
            .moneyTI(moneyTI)
            .build();
    }
}
