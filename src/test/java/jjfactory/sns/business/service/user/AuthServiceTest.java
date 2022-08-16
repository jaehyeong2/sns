package jjfactory.sns.business.service.user;

import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.user.UserRepository;
import jjfactory.sns.business.request.user.LoginReq;
import jjfactory.sns.business.request.user.UserCreate;
import jjfactory.sns.business.response.LoginRes;
import jjfactory.sns.global.handler.ex.BusinessException;
import jjfactory.sns.global.handler.ex.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class AuthServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입 성공")
    void signUp() {
        //given
        UserCreate tester = UserCreate.builder().username("test1").password("1234").build();

        //when
        authService.signUp(tester);
        List<String> usernames = userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        //then
        assertThat(usernames).containsExactly("test1");
    }

    //TODO 중복회원 가입실패
    @Test
    @DisplayName("아이디 중복이면 회원가입 실패")
    void signUpEx() {
        //given
        UserCreate tester = UserCreate.builder().username("test1").password("1234").build();

        //when
        authService.signUp(tester);
        List<String> usernames = userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        //then
        assertThat(usernames).containsExactly("test1");
    }

    @Test
    @DisplayName("로그인 성공")
    void login() {
        //given
        UserCreate tester = UserCreate.builder().username("test1").password("1234").build();
        authService.signUp(tester);

        LoginReq req = LoginReq.builder().username("test1").password("1234").build();

        //when
        LoginRes res = authService.login(req);
        String token = res.getToken();
        String refreshToken = res.getRefreshToken();

        //then
        assertThat(token).isNotNull();
        assertThat(refreshToken).isNotNull();
    }

    @Test
    @DisplayName("비번 틀리면 로그인 실패")
    void loginEx() {
        //given
        UserCreate tester = UserCreate.builder().username("test1").password("1234").build();
        authService.signUp(tester);

        LoginReq req = LoginReq.builder().username("test1").password("12345").build();

        String errMsg = ErrorCode.NOT_MATCH_PASSWORD.getMessage();

        //expected
        assertThatThrownBy(() ->{
            authService.login(req);
        }).isInstanceOf(BusinessException.class).hasMessage(errMsg);
    }

    @Test
    @DisplayName("아이디 중복체크")
    void duplicateCheck() {
        //given
        User test1 = User.builder().username("test1").build();
        em.persist(test1);

        String errMsg = ErrorCode.DUPLICATE_LOGIN_ID.getMessage();

        //expected
        assertThatThrownBy(() ->{
            authService.duplicateCheck("test1");
        }).isInstanceOf(BusinessException.class).hasMessage(errMsg);
        //expected
    }

}