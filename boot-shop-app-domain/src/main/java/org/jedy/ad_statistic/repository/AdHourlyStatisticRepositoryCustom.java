package org.jedy.ad_statistic.repository;

import org.jedy.ad_statistic.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic.dto.res.ResAdHourlyStatistic;
import org.springframework.data.domain.Page;

public interface AdHourlyStatisticRepositoryCustom {
    Page<ResAdHourlyStatistic> searchPage(AdStatisticSearchCondition condition, Integer page, Integer size);
}
