package org.jedy.adStatistic.repository.query;

import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdStatisticQueryRepository extends JpaRepository<AdHourlyStatistic, Long>, AdStatisticQueryRepositoryCustom {


}
