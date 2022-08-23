package jjfactory.sns.business.response;

import jjfactory.sns.business.domain.report.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ReportRes {
    private String username;
    private String reportedUsername;
    private String reason;
    private LocalDateTime createDate;

    @Builder
    public ReportRes(String username, String reportedUsername, String reason, LocalDateTime createDate) {
        this.username = username;
        this.reportedUsername = reportedUsername;
        this.reason = reason;
        this.createDate = createDate;
    }

    public ReportRes(Report report) {
        this.username = report.getReportUser().getUsername();
        this.reportedUsername = report.getReportedUser().getUsername();
        this.reason = report.getReportReason();
        this.createDate = report.getCreateDate();
    }
}
