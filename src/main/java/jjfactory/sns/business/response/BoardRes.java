package jjfactory.sns.business.response;

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
}
