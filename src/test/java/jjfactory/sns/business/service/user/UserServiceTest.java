package jjfactory.sns.business.service.user;

import jjfactory.sns.business.domain.user.Address;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.request.user.UserUpdate;
import jjfactory.sns.business.response.UserInfoRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("내 정보 수정페이지")
    void getMyInfo() {
        //given
        User wogud222 = User.builder()
                .username("wogud222")
                .password("1234")
                .name("이재형")
                .address(Address.builder().city("서울").zipCode("1234").build())
                .build();
        em.persist(wogud222);

        //when
        UserInfoRes myInfo = userService.getMyInfo(wogud222.getId());

        //then
        assertThat(myInfo.getUsername()).isEqualTo("wogud222");
        assertThat(myInfo.getAddress().getCity()).isEqualTo("서울");
    }


    @Test
    @DisplayName("내 정보 수정")
    void update() {
        //given
        User wogud222 = User.builder()
                .username("wogud222")
                .password("1234")
                .name("이재형")
                .email("test@naver.com")
                .address(Address.builder().city("서울").zipCode("1234").build())
                .build();
        em.persist(wogud222);

        UserUpdate dto = UserUpdate.builder()
                .email("change@gg.com")
                .build();

        //when
        userService.update(wogud222.getId(),dto);

        //then
        assertThat(wogud222.getEmail()).isEqualTo("change@gg.com");
    }
}