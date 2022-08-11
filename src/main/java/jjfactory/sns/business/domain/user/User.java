package jjfactory.sns.business.domain.user;

import jjfactory.sns.business.domain.BaseEntity;
import jjfactory.sns.business.request.user.UserCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String birth;

    private Address address;

    private int followCount;
    private int followerCount;

    @ElementCollection(fetch = FetchType.LAZY) //이거 없으면 에러남
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public User(String name, String username, String password, String email, String phone, String birth, Address address, int followCount, int followerCount, List<Role> roles, Gender gender) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
        this.followCount = followCount;
        this.followerCount = followerCount;
        this.roles = roles;
        this.gender = gender;
    }

    public static User createUser(UserCreate dto, String encPassword){
        return User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .password(encPassword)
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .build();
    }


}
