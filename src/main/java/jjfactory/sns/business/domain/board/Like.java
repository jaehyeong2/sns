package jjfactory.sns.business.domain.board;

import jjfactory.sns.business.domain.BaseEntity;
import jjfactory.sns.business.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @JoinColumn(name = "from_user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JoinColumn(name = "to_user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User toUser;
}
