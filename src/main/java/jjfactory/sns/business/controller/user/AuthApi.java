package jjfactory.sns.business.controller.user;

import io.swagger.annotations.ApiOperation;
import jjfactory.sns.business.request.user.LoginReq;
import jjfactory.sns.business.request.user.UserCreate;
import jjfactory.sns.business.response.LoginRes;
import jjfactory.sns.business.service.user.AuthService;
import jjfactory.sns.business.service.user.UserService;
import jjfactory.sns.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthApi {
    private final AuthService authService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<String> signUp(@Valid @RequestBody UserCreate dto){
        return new ApiResponse<>(authService.signUp(dto));
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public LoginRes login(@Valid @RequestBody LoginReq dto){
        return authService.login(dto);
    }
}
