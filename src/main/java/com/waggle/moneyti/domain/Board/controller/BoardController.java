package com.waggle.moneyti.domain.Board.controller;

import com.waggle.moneyti.domain.Board.dto.BoardRequest;
import com.waggle.moneyti.domain.Board.dto.BoardResponse.BoardList;
import com.waggle.moneyti.domain.Board.dto.BoardResponse.BoardPost;
import com.waggle.moneyti.domain.Board.service.BoardService;
import com.waggle.moneyti.domain.MoneyTI.service.MoneyTIService;
import com.waggle.moneyti.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Boards", description = "게시판 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final MoneyTIService moneyTIService;

    @GetMapping
    @Parameters({
        @Parameter(name = "moneyTI", description = "MoneyTI, Request Param 입니다.!"),
        @Parameter(name = "page", description = "리뷰의 페이지 번호, query string 입니다.!")
    })
    @Operation(summary = "게시글 조회", description = "MoneyTI 유형을 포함하여 게시글을 조회합니다.")
    public ApiResponse<List<BoardList>> getBoardList(@RequestParam(name = "page") Integer page,
        @RequestParam(name = "moneyTI") String moneyTI) {
        return ApiResponse.onSuccess(boardService.getBoardList(page, moneyTIService.findMoneyTI(moneyTI)));
    }

    @PostMapping
    @Operation(summary = "게시글 작성", description = "MoneyTI 유형에 해당하는 게시글을 작성합니다.")
    public ApiResponse<BoardPost> postBoard(@RequestBody BoardRequest request) {
        return ApiResponse.onSuccess(boardService.postBoard(request.getContent(),moneyTIService.findMoneyTI(request.getMoneyTI())));
    }
}
