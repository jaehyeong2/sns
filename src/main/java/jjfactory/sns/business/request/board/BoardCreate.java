package jjfactory.sns.business.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardCreate {
    private String title;
    private String content;
}
