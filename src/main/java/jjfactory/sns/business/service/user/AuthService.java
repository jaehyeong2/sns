package jjfactory.sns.business.service.user;

import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.user.UserRepository;
import jjfactory.sns.business.request.user.LoginReq;
import jjfactory.sns.business.request.user.UserCreate;
import jjfactory.sns.business.response.LoginRes;
import jjfactory.sns.global.auth.JwtTokenProvider;
import jjfactory.sns.global.handler.ex.BusinessException;
import jjfactory.sns.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    @Transactional
    public String signUp(UserCreate dto){
        duplicateCheck(dto.getUsername());
        String encPassword = encode(dto.getPassword());
        User user = User.createUser(dto, encPassword);

        userRepository.save(user);
        return "Y";
    }

    public LoginRes login(LoginReq dto){
        User user = userRepository.findByUsername(dto.getUsername());
        matchPassword(dto.getPassword(),user.getPassword());
        String token = createToken(user);
        String refreshToken = createRefreshToken();
        return new LoginRes(token,refreshToken);
    }

    private void matchPassword(String reqPassword, String savedPassword) {
        boolean result = encoder.matches(reqPassword, savedPassword);
        if(! result) throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
    }

    public void duplicateCheck(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
    }

    private String encode(String rawPassword) {
        String encPassword = encoder.encode(rawPassword);
        return encPassword;
    }

    public String createToken(User user){
        return tokenProvider.createToken(user.getUsername());
    }

    public String createRefreshToken(){
        return tokenProvider.createRefreshToken();
    }
}
