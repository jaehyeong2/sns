package jjfactory.sns.business.request.user;

import jjfactory.sns.business.domain.user.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class UserUpdate {
    private String email;
    private String phone;
    private String birth;

    private Address address;

    @Builder
    public UserUpdate(String email, String phone, String birth,Address address) {
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }
}
