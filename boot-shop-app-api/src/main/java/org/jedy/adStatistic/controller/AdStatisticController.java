package org.jedy.adStatistic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.ad_statistic.domain.AdHourlyStatistic;
import org.jedy.ad_statistic.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic.dto.res.ResUploadAdHourlyStatistic;
import org.jedy.ad_statistic.service.AdHourlyStatisticService;
import org.jedy.system_core.entity.PageResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/adStatistic")
@Slf4j
@RequiredArgsConstructor
public class AdStatisticController {

    private final AdHourlyStatisticService adHourlyStatisticService;


    @GetMapping("/search")
    @ResponseBody
    public PageResponse<ResAdHourlyStatistic> search(AdStatisticSearchCondition adStatisticSearchCondition){
        PageResponse<ResAdHourlyStatistic> searchResult = adHourlyStatisticService.searchPage(adStatisticSearchCondition, 0, 20);
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
