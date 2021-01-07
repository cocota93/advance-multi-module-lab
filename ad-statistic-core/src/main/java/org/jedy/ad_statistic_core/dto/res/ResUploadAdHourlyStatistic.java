package org.jedy.ad_statistic_core.dto.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.ad_statistic_core.domain.AdHourlyStatistic;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ResUploadAdHourlyStatistic {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private Integer hour;

    private Integer reqCount;

    private Integer resCount;

    private Integer clickCount;



    @Builder
    public ResUploadAdHourlyStatistic(Long id, LocalDate targetDate, Integer hour, Integer reqCount, Integer resCount, Integer clickCount) {
        this.id = id;
        this.targetDate = targetDate;
        this.hour = hour;
        this.reqCount = reqCount;
        this.resCount = resCount;
        this.clickCount = clickCount;
    }

    public ResUploadAdHourlyStatistic(AdHourlyStatistic adHourlyStatistic) {
        this.id = adHourlyStatistic.getId();
        this.targetDate = adHourlyStatistic.getTargetDate();
        this.hour = adHourlyStatistic.getHour();
        this.reqCount = adHourlyStatistic.getReqCount();
        this.resCount = adHourlyStatistic.getResCount();
        this.clickCount = adHourlyStatistic.getClickCount();
    }



}
