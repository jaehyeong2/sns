package jjfactory.sns.business.controller.report;

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

@RequiredArgsConstructor
@RequestMapping("/reports")
@RestController
public class ReportApi {
    private final ReportService reportService;

    @GetMapping("/{reportId}")
    public ReportRes findReport(@PathVariable Long reportId){
        return reportService.findReport(reportId);
    }

    @GetMapping("/{userId}")
    public Page<ReportRes> findReports(@PathVariable Long userId,
                                      @RequestParam(required = false,defaultValue = "1") int page,
                                      @RequestParam(required = false,defaultValue = "10") int size){
        return reportService.findReports(PageRequest.of(page,size),userId);
    }

    @PostMapping("")
    public ApiResponse<Long> create(@RequestBody ReportCreate dto){
        return new ApiResponse<>(reportService.create(dto));
    }

    @DeleteMapping("/{reportId}")
    public ApiResponse<String> delete(@PathVariable Long reportId){
        return new ApiResponse<>(reportService.delete(reportId));
    }


}
