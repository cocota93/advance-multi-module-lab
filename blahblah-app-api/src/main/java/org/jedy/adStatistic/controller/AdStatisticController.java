package org.jedy.adStatistic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.ad_statistic_core.domain.AdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.req.AdStatisticSearchCondition;
import org.jedy.ad_statistic_core.dto.req.ReqUploadAdStatistic;
import org.jedy.ad_statistic_core.dto.res.ResAdHourlyStatistic;
import org.jedy.ad_statistic_core.dto.res.ResUploadAdHourlyStatistic;
import org.jedy.ad_statistic_core.service.AdHourlyStatisticService;
import org.jedy.system_core.global.response.ResponseService;
import org.jedy.system_core.global.response.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/adStatistic")
@Slf4j
@RequiredArgsConstructor
public class AdStatisticController {

    private final AdHourlyStatisticService adHourlyStatisticService;
    private final ResponseService responseService;


    @GetMapping("/search")
    @ResponseBody
    public SingleResult<List<ResAdHourlyStatistic>> search(AdStatisticSearchCondition adStatisticSearchCondition){
        List<ResAdHourlyStatistic> search = adHourlyStatisticService.search(adStatisticSearchCondition);
        return responseService.getSingleResult(search);
    }

    @PostMapping("/upload")
    @ResponseBody
    public SingleResult<ResUploadAdHourlyStatistic> uploadAdStatistic(@RequestBody @Valid ReqUploadAdStatistic reqUploadAdStatistic) throws Exception {
        Long uploadEntityId = adHourlyStatisticService.doUpload(reqUploadAdStatistic);
        AdHourlyStatistic uploadEntity = adHourlyStatisticService.findById(uploadEntityId);
        return responseService.getSingleResult(new ResUploadAdHourlyStatistic(uploadEntity));
    }
}
