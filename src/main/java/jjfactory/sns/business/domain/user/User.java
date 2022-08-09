package jjfactory.sns.business.domain.user;

import jjfactory.sns.business.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String email;
    private String phone;
    private String birth;

    private Address address;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
