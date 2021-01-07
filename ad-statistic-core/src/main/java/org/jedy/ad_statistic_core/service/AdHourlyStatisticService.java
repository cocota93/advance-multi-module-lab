package org.jedy.ad_statistic_core.service;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jedy.ad_statistic_core.domain.AdHourlyStatistic;
import org.jedy.ad_statistic_core.domain.QAdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic_core.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic_core.dto.res.QResAdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic_core.repository.AdHourlyStatisticRepository;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.jedy.ad_statistic_core.domain.QAdHourlyStatistic.adHourlyStatistic;


@Service
@RequiredArgsConstructor
@Transactional
public class AdHourlyStatisticService {

    private final AdHourlyStatisticRepository adHourlyStatisticRepository;
    private final JPAQueryFactory queryFactory;


    public AdHourlyStatistic findById(Long id) {
        Optional<AdHourlyStatistic> findResult = adHourlyStatisticRepository.findById(id);
        AdHourlyStatistic adHourlyStatistic = findResult.orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getMessage() + " targetId : " + id));
        return adHourlyStatistic;
    }

    public Long doUpload(/*@Valid*/ ReqUploadAdStatistic reqUploadData){
        AdHourlyStatistic convertData = AdHourlyStatistic.UploadData()
                .reqUploadAdStatistic(reqUploadData)
                .build();
//
//        AdHourlyStatistic findResult = adHourlyStatisticRepository
//                .findByTargetDateEqualsAndHour(convertData.getTargetDate(), convertData.getHour()).orElse(null);
//
        AdHourlyStatistic findResult = adHourlyStatisticRepository
                .findByTargetDateEqualsAndHour(convertData.getTargetDate(), convertData.getHour()).orElse(null);


        if(findResult == null){
            findResult = adHourlyStatisticRepository.save(convertData);
        }else{
            findResult.copyExceptKeyAndUniqueKey(convertData);
        }

        return findResult.getId();
    }

    public List<ResAdHourlyStatistic> search(AdStatisticSearchCondition condition) {

        return queryFactory
                .select(
                        new QResAdHourlyStatistic(
                                adHourlyStatistic
                        )
                )
                .from(adHourlyStatistic)
                .where(
                        targetDateEq(condition.getTargetDate()),
                        hourEq(condition.getHour())
                )
                .fetch();
    }

    private BooleanExpression targetDateEq(LocalDate targetDate) {
        return targetDate != null ? adHourlyStatistic.targetDate.eq(targetDate) : null;
    }

    private BooleanExpression hourEq(Integer hour) {
        return hour != null ? adHourlyStatistic.hour.eq(hour) : null;
    }
}
