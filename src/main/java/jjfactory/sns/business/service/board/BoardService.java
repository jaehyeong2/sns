package jjfactory.sns.business.service.board;

import jjfactory.sns.business.domain.board.Board;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.board.BoardImageRepository;
import jjfactory.sns.business.repository.board.BoardQueryRepository;
import jjfactory.sns.business.repository.board.BoardRepository;
import jjfactory.sns.business.repository.user.UserRepository;
import jjfactory.sns.business.request.board.BoardCreate;
import jjfactory.sns.business.request.board.BoardUpdate;
import jjfactory.sns.business.response.BoardDetailRes;
import jjfactory.sns.business.response.BoardRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public BoardDetailRes findBoard(Long boardId){
        Board board = getBoard(boardId);
        return new BoardDetailRes(board);
    }

    @Transactional(readOnly = true)
    public Page<BoardRes> findBoards(Pageable pageable){
        return boardQueryRepository.findBoards(pageable);
    }

    //TODO 이미지 넣기
    public Long create(BoardCreate dto){
        User user = getUser(dto.getUserId());
        Board board = Board.create(dto, user);
        boardRepository.save(board);
        return board.getId();
    }

    public void delete(Long boardId){
        Board board = getBoard(boardId);
        board.delete();
    }

    public String update(Long boardId, BoardUpdate dto){
        Board board = getBoard(boardId);
        board.update(dto);
        return "Y";
    }

    private Board getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);
        return board;
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return user;
    }
}
