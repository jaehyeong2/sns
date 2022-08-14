package jjfactory.sns.business.service.board;

import jjfactory.sns.business.domain.board.Board;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.board.BoardRepository;
import jjfactory.sns.business.request.board.BoardCreate;
import jjfactory.sns.business.request.board.BoardUpdate;
import jjfactory.sns.business.response.BoardDetailRes;
import jjfactory.sns.business.response.BoardRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("게시물 조회")
    void findBoard() {
        //given
        User kim = User.builder().name("kim").build();
        em.persist(kim);
        Board board = Board.builder().title("테스트1").content("내용").user(kim).build();
        em.persist(board);

        //when
        BoardDetailRes findBoard = boardService.findBoard(board.getId());

        //then
        assertThat(findBoard.getContent()).isEqualTo("내용");
        assertThat(findBoard.getTitle()).isEqualTo("테스트1");
    }

    @Test
    @DisplayName("게시물 조회 페이징")
    void findBoards() {
        //given
        User kim = User.builder().name("kim").build();
        em.persist(kim);
        for (int i = 1; i < 16; i++) {
            Board board = Board.builder().title("테스트"+i).content("내용").user(kim).build();
            em.persist(board);
        }

        //when
        PageRequest pageable = PageRequest.of(0, 10);
        Page<BoardRes> boards = boardService.findBoards(pageable);
        List<String> titles = boards.stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());

        //then
        assertThat(boards.getTotalElements()).isEqualTo(15L);
        assertThat(boards.getTotalPages()).isEqualTo(2L);
        assertThat(titles.get(0)).isEqualTo("테스트15");
    }

    @Test
    @DisplayName("글 생성")
    void create() {
        //given
        User kim = User.builder().name("kim").build();
        em.persist(kim);

        BoardCreate dto = BoardCreate.builder().title("테스트1")
                .content("내용").userId(kim.getId()).build();
        //when
        boardService.create(dto);

        List<User> users = boardRepository.findAll()
                .stream().map(Board::getUser)
                .collect(Collectors.toList());
        //then
        assertThat(boardRepository.count()).isEqualTo(1L);
        assertThat(users).containsExactly(kim);

    }


    @Test
    @DisplayName("글 수정")
    void update() {
        //given
        User kim = User.builder().name("kim").build();
        em.persist(kim);
        Board board = Board.builder().title("테스트1").content("내용").user(kim).build();
        em.persist(board);

        BoardUpdate update = BoardUpdate.builder().title("수정되라").content("됬나염?").build();

        //when
        boardService.update(board.getId(),update);

        //then
        assertThat(board.getTitle()).isEqualTo("수정되라");
        assertThat(board.getContent()).isEqualTo("됬나염?");

    }
}