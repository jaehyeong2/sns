package jjfactory.sns.business.response;

import jjfactory.sns.business.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardDetailRes {
    private String title;
    private String content;
    private LocalDateTime createDate;
    private int viewCount;
    private String username;

    public BoardDetailRes(String title, String content, LocalDateTime createDate, int viewCount, String username) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.viewCount = viewCount;
        this.username = username;
    }

    public BoardDetailRes(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
        this.viewCount = board.getViewCount();
        this.username = board.getUser().getName();
    }
}
