package jjfactory.sns.business.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipCode;

    @Builder
    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
