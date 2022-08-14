package jjfactory.sns.business.request.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardCreate {
    private Long userId;
    private String title;
    private String content;

    @Builder
    public BoardCreate(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
