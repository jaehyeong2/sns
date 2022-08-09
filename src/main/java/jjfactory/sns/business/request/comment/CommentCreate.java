package jjfactory.sns.business.request.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentCreate {
    private Long boardId;
    private String content;
}
