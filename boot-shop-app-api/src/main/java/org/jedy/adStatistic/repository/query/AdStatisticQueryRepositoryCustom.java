package org.jedy.adStatistic.repository.query;

import org.jedy.ad_statistic.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic.dto.res.ResAdHourlyStatistic;
import org.springframework.data.domain.Page;

public interface AdStatisticQueryRepositoryCustom {
    Page<ResAdHourlyStatistic> searchPageComplex(AdStatisticSearchCondition condition, Integer page, Integer size);
}
