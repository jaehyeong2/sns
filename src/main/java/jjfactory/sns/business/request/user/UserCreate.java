package jjfactory.sns.business.request.user;

import jjfactory.sns.business.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class UserCreate {
    private String name;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
    private String phone;
    private String birth;


    @Builder
    public UserCreate(String name, String username, String password, String email, String phone, String birth) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
    }

    public UserCreate(User user) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
