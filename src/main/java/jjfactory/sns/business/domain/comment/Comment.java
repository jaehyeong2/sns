package jjfactory.sns.business.domain.comment;

import jjfactory.sns.business.domain.BaseEntity;
import jjfactory.sns.business.domain.board.Board;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.request.comment.CommentCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String content;

    private int likeCount;

    @JoinColumn(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    @Builder
    public Comment(User user, Board board, String content, int likeCount) {
        this.user = user;
        this.board = board;
        this.content = content;
        this.likeCount = likeCount;
    }

    public static Comment createComment(CommentCreate dto, User user, Board board){
        return Comment.builder()
                .user(user)
                .board(board)
                .likeCount(0)
                .content(dto.getContent())
                .build();
    }
}
