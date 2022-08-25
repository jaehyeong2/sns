package jjfactory.sns.business.request.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@Getter
public class ReportCreate {
    @ApiModelProperty(value = "신고 사유")
    @NotBlank
    private String reason;

    @ApiModelProperty(value = "신고 유저pk")
    @NotBlank
    private Long userId;
    @ApiModelProperty(value = "신고 당한 유저pk")
    @NotBlank
    private Long reportedUserId;

    @Builder
    public ReportCreate(String reason, Long userId, Long reportedUserId) {
        this.reason = reason;
        this.userId = userId;
        this.reportedUserId = reportedUserId;
    }

}
