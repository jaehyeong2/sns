package jjfactory.sns.business.controller.report;

import jjfactory.sns.business.service.report.ReportService;
import jjfactory.sns.business.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/reports")
@RestController
public class ReportApi {
    private final ReportService reportService;
}
