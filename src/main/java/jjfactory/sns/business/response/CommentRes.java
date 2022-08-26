package jjfactory.sns.business.response;

import jjfactory.sns.business.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CommentRes {
    private String username;
    private LocalDateTime createDate;
    private String content;
    private int likeCount;

    @Builder
    public CommentRes(String username, LocalDateTime createDate, String content, int likeCount) {
        this.username = username;
        this.createDate = createDate;
        this.content = content;
        this.likeCount = likeCount;
    }

    public CommentRes(Comment comment) {
        this.username = comment.getUser().getUsername();
        this.createDate = comment.getCreateDate();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
    }
}
