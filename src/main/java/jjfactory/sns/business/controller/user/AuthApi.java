package jjfactory.sns.business.controller.user;

import jjfactory.sns.business.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class AuthApi {
    private final UserService userService;
}
