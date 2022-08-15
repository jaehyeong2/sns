package jjfactory.sns.business.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginReq {
    private String username;
    private String password;
}
