package jjfactory.sns.business.domain.user;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipCode;

}
