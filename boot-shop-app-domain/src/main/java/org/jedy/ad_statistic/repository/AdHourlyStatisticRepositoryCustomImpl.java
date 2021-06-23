package org.jedy.ad_statistic.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.jedy.ad_statistic.domain.QAdHourlyStatistic;
import org.jedy.ad_statistic.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic.dto.res.QResAdHourlyStatistic;
import org.jedy.ad_statistic.dto.res.ResAdHourlyStatistic;
import org.jedy.member.domain.Member;
import org.jedy.productOrder.domain.ProductOrder;
import org.jedy.productOrder.repository.ProductOrderRepositoryCustom;
import org.jedy.system_core.support.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static org.jedy.ad_statistic.domain.QAdHourlyStatistic.adHourlyStatistic;
import static org.jedy.member.domain.QMember.member;

public class AdHourlyStatisticRepositoryCustomImpl extends Querydsl4RepositorySupport implements AdHourlyStatisticRepositoryCustom {
    public AdHourlyStatisticRepositoryCustomImpl() { super(AdHourlyStatistic.class); }


    @Override
    public Page<ResAdHourlyStatistic> searchPage(AdStatisticSearchCondition condition, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        List<ResAdHourlyStatistic> content = select(
                        new QResAdHourlyStatistic(
                                adHourlyStatistic
                        )
                )
                .from(adHourlyStatistic)
                .where(
                        targetDateEq(condition.getTargetDate()),
                        hourEq(condition.getHour())
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = select(member)
                .from(member)
                .where(
                        targetDateEq(condition.getTargetDate()),
                        hourEq(condition.getHour())
                );

        Page<ResAdHourlyStatistic> pageList = PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);

        return pageList;
    }

    private BooleanExpression targetDateEq(LocalDate targetDate) {
        return targetDate != null ? QAdHourlyStatistic.adHourlyStatistic.targetDate.eq(targetDate) : null;
    }

    private BooleanExpression hourEq(Integer hour) {
        return hour != null ? QAdHourlyStatistic.adHourlyStatistic.hour.eq(hour) : null;
    }
}
