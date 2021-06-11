package org.jedy.ad_statistic.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReqUploadAdStatistic {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    @NotNull
    private Integer hour;

    private Integer reqCount;

    private Integer resCount;

    private Integer clickCount;


    public ReqUploadAdStatistic(AdHourlyStatistic adHourlyStatistic) {
        this.targetDate = adHourlyStatistic.getTargetDate();
        this.hour = adHourlyStatistic.getHour();
        this.reqCount = adHourlyStatistic.getReqCount();
        this.resCount = adHourlyStatistic.getResCount();
        this.clickCount = adHourlyStatistic.getClickCount();
    }
}
