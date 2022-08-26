package jjfactory.sns.business.repository.comment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.sns.business.response.CommentRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.sns.business.domain.comment.QComment.*;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<CommentRes> findComments(Long boardId, Pageable pageable){
        List<CommentRes> comments = queryFactory.select(Projections.constructor(CommentRes.class, comment))
                .from(comment)
                .where(comment.board.id.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(CommentRes.class, comment))
                .from(comment)
                .where(comment.board.id.eq(boardId))
                .fetch().size();

        return new PageImpl<>(comments,pageable,total);
    }
}
