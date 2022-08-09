package jjfactory.sns.business.service.board;

import jjfactory.sns.business.repository.board.BoardImageRepository;
import jjfactory.sns.business.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {
    private BoardRepository boardRepository;
    private BoardImageRepository boardImageRepository;

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
