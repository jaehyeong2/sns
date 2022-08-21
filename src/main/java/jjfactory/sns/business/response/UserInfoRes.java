package jjfactory.sns.business.response;

import jjfactory.sns.business.domain.user.Address;
import jjfactory.sns.business.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserInfoRes {
    private String username;
    private String email;
    private String name;
    private String birth;
    private String phone;
    private Address address;

    public UserInfoRes(String username, String email, String name, String birth, String phone, Address address) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
    }

    public UserInfoRes(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }
}
