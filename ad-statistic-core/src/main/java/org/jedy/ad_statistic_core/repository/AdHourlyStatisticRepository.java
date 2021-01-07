package org.jedy.ad_statistic_core.repository;


import org.jedy.ad_statistic_core.domain.AdHourlyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AdHourlyStatisticRepository extends JpaRepository<AdHourlyStatistic, Long> {
    Optional<AdHourlyStatistic> findByTargetDateEqualsAndHour(LocalDate targetDate, Integer hour);
}
