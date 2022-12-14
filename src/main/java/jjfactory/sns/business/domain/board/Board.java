package jjfactory.sns.business.domain.board;

import jjfactory.sns.business.domain.BaseEntity;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.request.board.BoardCreate;
import jjfactory.sns.business.request.board.BoardUpdate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;
    @Lob
    private String content;

    private int viewCount;
    private int likeCount;

    @Builder
    public Board(User user, String title, String content, int viewCount, int likeCount) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    public static Board create(BoardCreate dto,User user){
        return Board.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .viewCount(0)
                .likeCount(0)
                .build();
    }

    public void update(BoardUpdate dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
