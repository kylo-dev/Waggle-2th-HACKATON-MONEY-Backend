package com.waggle.moneyti.domain.Board.service;

import static com.waggle.moneyti.domain.Board.dto.BoardResponse.BoardList;
import static com.waggle.moneyti.domain.Board.dto.BoardResponse.toBoardPost;
import static com.waggle.moneyti.domain.Board.dto.BoardResponse.toEntity;

import com.waggle.moneyti.domain.Board.Board;
import com.waggle.moneyti.domain.Board.dto.BoardRequest;
import com.waggle.moneyti.domain.Board.dto.BoardResponse;
import com.waggle.moneyti.domain.Board.dto.BoardResponse.BoardPost;
import com.waggle.moneyti.domain.Board.repository.BoardRepository;
import com.waggle.moneyti.domain.enums.MoneyTI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 조회
    public List<BoardList> getBoardList(Integer page, String moneyTI) {

        PageRequest pageRequest = PageRequest.of(page, 10);
        List<Board> boardList = boardRepository.findAllByMoneyTI(MoneyTI.valueOf(moneyTI),
            pageRequest);

        return boardList.stream()
            .map(BoardResponse::toBoardList)
            .collect(Collectors.toList());
    }


    // 게시글 저장
    @Transactional
    public BoardPost postBoard(BoardRequest request) {

        Board newBoard = boardRepository.save(toEntity(request));

        return toBoardPost(newBoard.getId());
    }
}
