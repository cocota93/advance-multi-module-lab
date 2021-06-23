package org.jedy.ad_statistic.repository;


import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AdHourlyStatisticRepository extends JpaRepository<AdHourlyStatistic, Long>, AdHourlyStatisticRepositoryCustom {
    Optional<AdHourlyStatistic> findByTargetDateEqualsAndHour(LocalDate targetDate, Integer hour);
}
