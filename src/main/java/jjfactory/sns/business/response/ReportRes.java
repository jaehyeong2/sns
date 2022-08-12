package jjfactory.sns.business.response;

import jjfactory.sns.business.domain.report.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReportRes {
    private Long userId;
    private Long reportedUserId;
    private String reason;

    public ReportRes(Long userId, Long reportedUserId, String reason) {
        this.userId = userId;
        this.reportedUserId = reportedUserId;
        this.reason = reason;
    }

    public ReportRes(Report report) {
        this.userId = report.getReportUser().getId();
        this.reportedUserId = report.getReportedUser().getId();
        this.reason = report.getReportReason();
    }
}
