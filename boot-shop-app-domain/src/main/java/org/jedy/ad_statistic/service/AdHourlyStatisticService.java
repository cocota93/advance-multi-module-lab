package org.jedy.ad_statistic.service;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jedy.ad_statistic.repository.AdHourlyStatisticRepository;
import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.jedy.ad_statistic.domain.QAdHourlyStatistic;
import org.jedy.ad_statistic.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic.dto.res.QResAdHourlyStatistic;
import org.jedy.ad_statistic.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic.repository.AdHourlyStatisticRepositoryCustom;
import org.jedy.system_core.entity.PageResponse;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.jedy.ad_statistic.domain.QAdHourlyStatistic.adHourlyStatistic;


@Service
@Transactional
@RequiredArgsConstructor
public class AdHourlyStatisticService {

    private AdHourlyStatisticRepository adHourlyStatisticRepository;


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

    public PageResponse<ResAdHourlyStatistic> searchPage(AdStatisticSearchCondition adStatisticSearchCondition, int page, int size) {
        Page<ResAdHourlyStatistic> resAdHourlyStatistics = adHourlyStatisticRepository.searchPage(adStatisticSearchCondition, page, size);
        return new PageResponse<>(resAdHourlyStatistics);
    }

    private BooleanExpression targetDateEq(LocalDate targetDate) {
        return targetDate != null ? QAdHourlyStatistic.adHourlyStatistic.targetDate.eq(targetDate) : null;
    }

    private BooleanExpression hourEq(Integer hour) {
        return hour != null ? QAdHourlyStatistic.adHourlyStatistic.hour.eq(hour) : null;
    }
}
