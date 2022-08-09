package jjfactory.sns.business.repository.report;

import jjfactory.sns.business.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
