package jjfactory.sns.business.controller.board;

import jjfactory.sns.business.request.board.BoardCreate;
import jjfactory.sns.business.request.board.BoardUpdate;
import jjfactory.sns.business.response.BoardDetailRes;
import jjfactory.sns.business.response.BoardRes;
import jjfactory.sns.business.service.board.BoardService;
import jjfactory.sns.business.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.*;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardApi {
    private final BoardService boardService;

    @GetMapping("/{id}")
    public BoardDetailRes findBoard(@PathVariable Long id){
        return boardService.findBoard(id);
    }

    @GetMapping("")
    public Page<BoardRes> findBoards(@PageableDefault(size = 10, sort = "createDate", direction = DESC) Pageable pageable){
        return boardService.findBoards(pageable);
    }


    //TODO 시큐리티 적용
    @PostMapping("")
    public Long createBoard(@RequestBody BoardCreate dto){
        return boardService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable  Long id){
        boardService.delete(id);
    }

    @PutMapping("/{id}")
    public void modifyBoard(@PathVariable Long id, @RequestBody BoardUpdate dto){
        boardService.update(id,dto);
    }

}
