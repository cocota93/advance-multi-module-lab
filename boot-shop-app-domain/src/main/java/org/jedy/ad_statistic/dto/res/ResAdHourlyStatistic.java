package org.jedy.ad_statistic.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ResAdHourlyStatistic {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private Integer hour;

    private Integer reqCount;

    private Integer resCount;

    private Integer clickCount;

    @QueryProjection
    public ResAdHourlyStatistic(AdHourlyStatistic adHourlyStatistic) {
        this.id = adHourlyStatistic.getId();
        this.targetDate = adHourlyStatistic.getTargetDate();
        this.hour = adHourlyStatistic.getHour();
        this.reqCount = adHourlyStatistic.getReqCount();
        this.resCount = adHourlyStatistic.getResCount();
        this.clickCount = adHourlyStatistic.getClickCount();
    }

    @Builder
    public ResAdHourlyStatistic(Long id, LocalDate targetDate, Integer hour, Integer reqCount, Integer resCount, Integer clickCount) {
        this.id = id;
        this.targetDate = targetDate;
        this.hour = hour;
        this.reqCount = reqCount;
        this.resCount = resCount;
        this.clickCount = clickCount;
    }
}
