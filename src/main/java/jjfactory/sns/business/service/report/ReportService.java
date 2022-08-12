package jjfactory.sns.business.service.report;

import jjfactory.sns.business.domain.report.Report;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.report.ReportRepository;
import jjfactory.sns.business.repository.user.UserRepository;
import jjfactory.sns.business.request.report.ReportCreate;
import jjfactory.sns.business.response.ReportRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ReportRes findOne(Long id){
        Report report = getReport(id);
        return new ReportRes(report);
    }


    @Transactional(readOnly = true)
    public Page<Object> findObjects(Pageable pageable){
        return null;
    }

    public Long create(ReportCreate dto){
        User user = getUser(dto.getUserId());
        User reported = getUser(dto.getReportedUserId());
        Report report = Report.reportUser(dto, reported, user);
        reportRepository.save(report);
        return report.getId();
    }

    public String delete(Long id){
        Report report = getReport(id);
        report.delete();
        return "Y";
    }

    public String update(){
        return "Y";
    }

    private Report getReport(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return report;
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return user;
    }
}
