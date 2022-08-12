package jjfactory.sns.business.response;

import jjfactory.sns.business.domain.report.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReportRes {
    private String username;
    private String reportedUsername;
    private String reason;

    public ReportRes(String username, String reportedUsername, String reason) {
        this.username = username;
        this.reportedUsername = reportedUsername;
        this.reason = reason;
    }

    public ReportRes(Report report) {
        this.username = report.getReportUser().getUsername();
        this.reportedUsername = report.getReportedUser().getUsername();
        this.reason = report.getReportReason();
    }
}
