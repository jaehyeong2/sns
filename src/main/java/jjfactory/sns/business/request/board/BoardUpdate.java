package jjfactory.sns.business.request.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardUpdate {
    private String title;
    private String content;

    @Builder
    public BoardUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
