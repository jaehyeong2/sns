package jjfactory.sns.business.response;

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
}
