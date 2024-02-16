package com.waggle.moneyti.domain.Board.service;

import com.waggle.moneyti.domain.Board.Board;
import com.waggle.moneyti.domain.Board.dto.BoardRequest;
import com.waggle.moneyti.domain.Board.dto.BoardResponse;
import com.waggle.moneyti.domain.Board.repository.BoardRepository;
import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import com.waggle.moneyti.domain.MoneyTI.repository.MoneyTIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.waggle.moneyti.domain.Board.dto.BoardResponse.toBoardPost;
import static com.waggle.moneyti.domain.Board.dto.BoardResponse.toEntity;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MoneyTIRepository moneyTIRepository;

    // 게시글 조회
    public List<BoardResponse.BoardList> getBoardList(Integer page, MoneyTI moneyTI) {

        PageRequest pageRequest = PageRequest.of(page, 10);
        List<Board> boardList = boardRepository.findAllByMoneyTI(moneyTI,
            pageRequest);

        return boardList.stream()
            .map(BoardResponse::toBoardList)
            .collect(Collectors.toList());
    }


    // 게시글 저장
    @Transactional
    public BoardResponse.BoardPost postBoard(String content, MoneyTI moneyTI) {

        Board newBoard = boardRepository.save(toEntity(moneyTI, content));

        return toBoardPost(newBoard.getId());
    }
}
