package jjfactory.sns.business.response;

import jjfactory.sns.business.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardRes {
    private String title;
    private LocalDateTime createDate;
    private int viewCount;
    private String username;

    public BoardRes(String title, LocalDateTime createDate, int viewCount, String username) {
        this.title = title;
        this.createDate = createDate;
        this.viewCount = viewCount;
        this.username = username;
    }

    public BoardRes(Board board) {
        this.title = board.getTitle();
        this.createDate = board.getCreateDate();
        this.viewCount = board.getViewCount();
        this.username = board.getUser().getName();
    }
}
