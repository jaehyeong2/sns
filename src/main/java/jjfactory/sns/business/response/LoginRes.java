package jjfactory.sns.business.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRes {
    private String token;
    private String refreshToken;

    @Builder
    public LoginRes(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
