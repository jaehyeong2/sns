package jjfactory.sns.business.repository.board;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.sns.business.domain.board.QBoard;
import jjfactory.sns.business.response.BoardDetailRes;
import jjfactory.sns.business.response.BoardRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.sns.business.domain.board.QBoard.*;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<BoardRes> findBoards(Pageable pageable){
        List<BoardRes> boards = queryFactory.select(Projections.constructor(BoardRes.class, board))
                .from(board)
                .orderBy(board.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(BoardRes.class, board))
                .from(board).fetch().size();

        return new PageImpl<>(boards,pageable,total);
    }
}
