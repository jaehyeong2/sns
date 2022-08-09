package jjfactory.sns.business.service.user;

import jjfactory.sns.business.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    public Object findOne(){
        return null;
    }

    public Page<Object> findObjects(Pageable pageable){
        return null;
    }

    public Long create(){
        return null;
    }

    public String delete(){
        return "Y";
    }

    public String update(){
        return "Y";
    }
}
