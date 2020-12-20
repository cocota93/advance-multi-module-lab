package org.jedy.oembed.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.oembed_core.domain.Oembed;
import org.jedy.oembed_core.service.OembedServiceImpl;
import org.jedy.system_core.global.response.CommonResult;
import org.jedy.system_core.global.response.ResponseService;
import org.jedy.system_core.global.response.SingleResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/oembed")
@RequiredArgsConstructor
@Slf4j
public class OembedController {

    private final OembedServiceImpl oembedService;
    private final ResponseService responseService;


//    @GetMapping(value = "/search")
//    public String searchPage(){
//        log.info("asd");
//        return "/oembed/search";
//    }

    @GetMapping(value = "/search")
    public String search(Model model, String searchUrl) throws Exception {
//TODO  파라미터 널처리
        if(!StringUtils.isEmpty(searchUrl)){
            Oembed oembed = oembedService.searchFromExternal(searchUrl);
            model.addAttribute("searchResult",oembed);
        }
        return "/oembed/search";
    }
}
