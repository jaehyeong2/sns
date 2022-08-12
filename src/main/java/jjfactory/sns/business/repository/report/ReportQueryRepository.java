package jjfactory.sns.business.repository.report;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.sns.business.domain.report.QReport;
import jjfactory.sns.business.response.ReportRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.sns.business.domain.report.QReport.*;

@RequiredArgsConstructor
@Repository
public class ReportQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<ReportRes> findReportsByUserId(Pageable pageable,Long userId){
        List<ReportRes> reports = queryFactory.select(Projections.constructor(ReportRes.class, report))
                .from(report)
                .orderBy(report.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        int total = queryFactory.select(Projections.constructor(ReportRes.class, report))
                .from(report).fetch().size();

        return new PageImpl<>(reports,pageable,total);
    }
}
