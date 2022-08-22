package jjfactory.sns.business.controller.user;

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

    @GetMapping("/{userId}")
    public UserInfoRes getUpdateForm(@PathVariable Long userId){
        return userService.getUpdateForm(userId);
    }

    @DeleteMapping("/{userId}")
    public String withdraw(@PathVariable Long userId){
        return userService.withdraw(userId);
    }

    @PutMapping("/{userId}")
    public String updateUserInfo(@PathVariable Long userId,
                                 @RequestBody UserUpdate dto){
        return userService.update(userId,dto);
    }

}
