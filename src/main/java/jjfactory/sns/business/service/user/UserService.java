package jjfactory.sns.business.service.user;

import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.user.UserRepository;
import jjfactory.sns.business.request.user.UserUpdate;
import jjfactory.sns.business.response.UserInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    //내정보 수정 get
    public UserInfoRes getUpdateForm(Long userId){
        User user = getUser(userId);
        return new UserInfoRes(user);
    }

    public String withdraw(Long userId){
        User user = getUser(userId);
        user.delete();
        return "Y";
    }

    public String update(Long userId,UserUpdate dto){
        User user = getUser(userId);
        user.updateUserInfo(dto);
        return "Y";
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return user;
    }
}
