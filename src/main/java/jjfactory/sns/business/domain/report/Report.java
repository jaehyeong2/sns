package jjfactory.sns.business.domain.report;

import jjfactory.sns.business.domain.BaseEntity;
import jjfactory.sns.business.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "reporter_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User reportUser;

    @JoinColumn(name = "reported_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User reportedUser;

    private String reportReason;
}
