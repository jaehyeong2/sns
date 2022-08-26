package jjfactory.sns.business.service.comment;

import jjfactory.sns.business.domain.board.Board;
import jjfactory.sns.business.domain.comment.Comment;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.board.BoardRepository;
import jjfactory.sns.business.repository.comment.CommentQueryRepository;
import jjfactory.sns.business.repository.comment.CommentRepository;
import jjfactory.sns.business.repository.user.UserRepository;
import jjfactory.sns.business.request.comment.CommentCreate;
import jjfactory.sns.business.request.comment.CommentUpdate;
import jjfactory.sns.business.response.CommentRes;
import jjfactory.sns.global.handler.ex.BusinessException;
import jjfactory.sns.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    
    //TODO  무한 대댓글
    @Transactional(readOnly = true)
    public Page<CommentRes> findComments(Long boardId,Pageable pageable){
        return commentQueryRepository.findComments(boardId,pageable);
    }

    public Long create(CommentCreate dto,Long userId){
        User user = getUser(userId);
        Board board = getBoard(dto.getBoardId());
        Comment comment = Comment.createComment(dto, user, board);
        commentRepository.save(comment);
        return comment.getId();
    }

    public String delete(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        });
        comment.delete();
        return "Y";
    }

    public String update(CommentUpdate dto,Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
        });
        comment.modify(dto.getContent());
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
