package jjfactory.sns.business.repository.comment;

import jjfactory.sns.business.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
