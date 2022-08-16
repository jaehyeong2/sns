package jjfactory.sns.business.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginReq {
    private String username;
    private String password;

    @Builder
    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
