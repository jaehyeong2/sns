package jjfactory.sns.business.domain.report;

import jjfactory.sns.business.domain.BaseEntity;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.request.report.ReportCreate;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public Report(User reportUser, User reportedUser, String reportReason) {
        this.reportUser = reportUser;
        this.reportedUser = reportedUser;
        this.reportReason = reportReason;
    }

    public static Report reportUser(ReportCreate dto,User reportedUser ,User reportUser){
        reportedUser.addWarningCount();

        Report report = Report.builder()
                .reportedUser(reportedUser)
                .reportUser(reportUser)
                .reportReason(dto.getReason())
                .build();

        return report;
    }
}
