package org.jedy.ad_statistic.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jedy.ad_statistic.dto.req.ReqUploadAdStatistic;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"targetDate","hour"}
                )
        }
)
@Getter
public class AdHourlyStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_hourly_statistic_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate targetDate;

    @Column(nullable = false)
    private Integer hour;

    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer reqCount;

    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer resCount;

    @Column(nullable = false, columnDefinition = "Integer default 0")
    private Integer clickCount;

    @Builder
    public AdHourlyStatistic(Long id, LocalDate targetDate, Integer hour, Integer reqCount, Integer resCount, Integer clickCount) {
        this.id = id;
        this.targetDate = targetDate;
        this.hour = hour;
        this.reqCount = reqCount;
        this.resCount = resCount;
        this.clickCount = clickCount;

        checkAssembleData();
    }

    @Builder(builderClassName = "WhenCreateTestData", builderMethodName = "WhenCreateTestData")
    public AdHourlyStatistic(LocalDate targetDate, Integer hour, Integer reqCount, Integer resCount, Integer clickCount) {
        this.targetDate = targetDate;
        this.hour = hour;
        this.reqCount = reqCount;
        this.resCount = resCount;
        this.clickCount = clickCount;
    }

    @Builder(builderClassName = "UploadData", builderMethodName = "UploadData")
    public AdHourlyStatistic(ReqUploadAdStatistic reqUploadAdStatistic) {
        this.targetDate = reqUploadAdStatistic.getTargetDate();
        this.hour = reqUploadAdStatistic.getHour();
        this.reqCount = reqUploadAdStatistic.getReqCount();
        this.resCount = reqUploadAdStatistic.getResCount();
        this.clickCount = reqUploadAdStatistic.getClickCount();

        checkAssembleData();
    }


    public void copyExceptKeyAndUniqueKey(AdHourlyStatistic from) {
        this.reqCount = from.getReqCount();
        this.resCount = from.getResCount();
        this.clickCount = from.getClickCount();

        checkAssembleData();
    }

    private void checkAssembleData(){
//        if(this.reqCount < this.resCount){
//            throw new AdStatisticEntityInvalidAssembleDataException("reqCount : " + reqCount + ", resCount : " + resCount);
//        }
    }
}
