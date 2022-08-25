package jjfactory.sns.business.controller.report;

import io.swagger.annotations.ApiOperation;
import jjfactory.sns.business.request.report.ReportCreate;
import jjfactory.sns.business.response.ReportRes;
import jjfactory.sns.business.service.report.ReportService;
import jjfactory.sns.business.service.user.UserService;
import jjfactory.sns.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import javax.validation.Valid;


//TODO 스웨거 추가
@RequiredArgsConstructor
@RequestMapping("/reports")
@RestController
public class ReportApi {
    private final ReportService reportService;

    @ApiOperation(value = "신고내역 단건 상세보기")
    @GetMapping("/{reportId}")
    public ReportRes findReport(@PathVariable Long reportId){
        return reportService.findReport(reportId);
    }

    @ApiOperation(value = "개인 전체 신고한 내역 보기")
    @GetMapping("/{userId}")
    public Page<ReportRes> findReports(@PathVariable Long userId,
                                      @RequestParam(required = false,defaultValue = "1") int page,
                                      @RequestParam(required = false,defaultValue = "10") int size){
        return reportService.findReports(PageRequest.of(page,size),userId);
    }

    @ApiOperation(value = "신고하기")
    @PostMapping("")
    public ApiResponse<Long> create(@RequestBody @Valid ReportCreate dto){
        return new ApiResponse<>(reportService.create(dto));
    }

    @ApiOperation(value = "신고글 삭제하기")
    @DeleteMapping("/{reportId}")
    public ApiResponse<String> delete(@PathVariable Long reportId){
        return new ApiResponse<>(reportService.delete(reportId));
    }


}
