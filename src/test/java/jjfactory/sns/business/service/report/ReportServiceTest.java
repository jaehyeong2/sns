package jjfactory.sns.business.service.report;

import jjfactory.sns.business.domain.DeleteStatus;
import jjfactory.sns.business.domain.report.Report;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.repository.report.ReportRepository;
import jjfactory.sns.business.repository.user.UserRepository;
import jjfactory.sns.business.request.report.ReportCreate;
import jjfactory.sns.business.response.ReportRes;
import jjfactory.sns.global.handler.ex.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ReportServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    ReportService reportService;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("신고 단건 조회")
    void findOne() {
        //given
        User lee = User.builder().username("lee22").build();
        User kim = User.builder().username("kim1234").build();
        em.persist(kim);
        em.persist(lee);

        Report report = Report.builder().reportUser(kim).reportedUser(lee).build();
        reportRepository.save(report);

        //when
        ReportRes findReport = reportService.findOne(report.getId());

        //then
        assertThat(findReport.getUsername()).isEqualTo("kim1234");
        assertThat(findReport.getReportedUsername()).isEqualTo("lee22");

    }

    @Test
    @DisplayName("신고 내역 조회 페이징")
    void findReports() {
        //given
        User lee = User.builder().username("lee22").build();
        User kim = User.builder().username("kim1234").build();
        userRepository.save(kim);
        userRepository.save(lee);

        for (int i = 0; i < 25; i++) {
            Report report = Report.builder().reportUser(kim).reportedUser(lee).build();
            reportRepository.save(report);
        }

        //when
        PageRequest pageable = PageRequest.of(0, 10);
        Page<ReportRes> reports = reportService.findReports(pageable, kim.getId());

        //then
        assertThat(reports.getTotalPages()).isEqualTo(3L);
        assertThat(reports.getTotalElements()).isEqualTo(25L);
    }

    @Test
    @DisplayName("신고 성공")
    void create() {
        //given
        User lee = User.builder().username("lee22").build();
        User kim = User.builder().username("kim1234").build();
        userRepository.save(kim);
        userRepository.save(lee);

        ReportCreate dto = ReportCreate.builder().reportedUserId(lee.getId())
                .userId(kim.getId()).reason("그냥").build();

        //when
        reportService.create(dto);
        List<String> reasons = reportRepository.findAll().stream().map(Report::getReportReason)
                .collect(Collectors.toList());

        //then
        assertThat(reportRepository.count()).isEqualTo(1L);
        assertThat(reasons).containsExactly("그냥");
    }

    @Test
    @DisplayName("같은 유저면 신고 실패")
    void createEx() {
        //given
        User lee = User.builder().username("lee22").build();
        userRepository.save(lee);

        ReportCreate dto = ReportCreate.builder().reportedUserId(lee.getId())
                .userId(lee.getId()).reason("그냥").build();

        //expected
        assertThatThrownBy(() -> {
            reportService.create(dto);
        }).isInstanceOf(BusinessException.class);

    }

    @Test
    @DisplayName("신고 삭제")
    void delete() {
        //given
        User lee = User.builder().username("lee22").build();
        User kim = User.builder().username("kim1234").build();
        userRepository.save(kim);
        userRepository.save(lee);

        Report report = Report.builder().reportUser(kim).reportedUser(lee).build();
        reportRepository.save(report);

        //when
        reportService.delete(report.getId());

        //then
        assertThat(report.getDeleteStatus()).isEqualTo(DeleteStatus.DELETED);
    }
}