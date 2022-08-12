package jjfactory.sns.business.request.report;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ReportCreate {
    private String reason;
    private Long userId;
    private Long reportedUserId;

    @Builder
    public ReportCreate(String reason, Long userId, Long reportedUserId) {
        this.reason = reason;
        this.userId = userId;
        this.reportedUserId = reportedUserId;
    }

}
