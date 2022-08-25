package jjfactory.sns.business.controller.user;

import io.swagger.annotations.ApiOperation;
import jjfactory.sns.business.request.user.UserUpdate;
import jjfactory.sns.business.response.UserInfoRes;
import jjfactory.sns.business.service.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserApi {
    private final UserService userService;

    @ApiOperation(value = "유저 정보수정 get", notes = "유저 정보수정페이지에서 정보를 셀렉트해오는 api입니다.")
    @GetMapping("/{userId}")
    public UserInfoRes getUpdateForm(@PathVariable Long userId){
        return userService.getUpdateForm(userId);
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/{userId}")
    public String withdraw(@PathVariable Long userId){
        return userService.withdraw(userId);
    }

    @ApiOperation(value = "개인정보 수정")
    @PutMapping("/{userId}")
    public String updateUserInfo(@PathVariable Long userId,
                                 @RequestBody UserUpdate dto){
        return userService.update(userId,dto);
    }

}
