package jjfactory.sns.business.request.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class BoardCreate {
    @NotBlank
    private Long userId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @Builder
    public BoardCreate(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
