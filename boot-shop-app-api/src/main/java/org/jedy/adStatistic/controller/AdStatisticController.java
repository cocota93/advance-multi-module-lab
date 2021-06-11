package org.jedy.adStatistic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.adStatistic.repository.query.AdStatisticQueryRepositoryImpl;
import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.jedy.ad_statistic.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic.dto.res.ResUploadAdHourlyStatistic;
import org.jedy.ad_statistic.service.AdHourlyStatisticService;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/adStatistic")
@Slf4j
@RequiredArgsConstructor
public class AdStatisticController {

    private final AdHourlyStatisticService adHourlyStatisticService;
    private final AdStatisticQueryRepositoryImpl adStatisticQueryRepository;


    @GetMapping("/search")
    @ResponseBody
    public Page<ResAdHourlyStatistic> search(AdStatisticSearchCondition adStatisticSearchCondition){
        Page<ResAdHourlyStatistic> searchResult = adStatisticQueryRepository.searchPageComplex(adStatisticSearchCondition, 0, 20);
        return searchResult;
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResUploadAdHourlyStatistic uploadAdStatistic(@RequestBody @Valid ReqUploadAdStatistic reqUploadAdStatistic, Model model) throws Exception {
        Long uploadEntityId = adHourlyStatisticService.doUpload(reqUploadAdStatistic);
        AdHourlyStatistic uploadEntity = adHourlyStatisticService.findById(uploadEntityId);
        ResUploadAdHourlyStatistic resUploadAdHourlyStatistic = new ResUploadAdHourlyStatistic(uploadEntity);
        return resUploadAdHourlyStatistic;
    }
}
