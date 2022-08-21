package jjfactory.sns.business.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class LoginReq {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @Builder
    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
