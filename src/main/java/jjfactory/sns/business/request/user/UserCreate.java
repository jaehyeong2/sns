package jjfactory.sns.business.request.user;

import jjfactory.sns.business.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreate {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String birth;

    public UserCreate(User user) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
