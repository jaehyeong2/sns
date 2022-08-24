package jjfactory.sns.business.request.report;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@Getter
public class ReportCreate {
    @NotBlank
    private String reason;
    @NotBlank
    private Long userId;
    @NotBlank
    private Long reportedUserId;

    @Builder
    public ReportCreate(String reason, Long userId, Long reportedUserId) {
        this.reason = reason;
        this.userId = userId;
        this.reportedUserId = reportedUserId;
    }

}
