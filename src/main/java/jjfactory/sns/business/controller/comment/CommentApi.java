package jjfactory.sns.business.controller.comment;

import jjfactory.sns.business.service.comment.CommentService;
import jjfactory.sns.business.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentApi {
    private final CommentService commentService;
}
