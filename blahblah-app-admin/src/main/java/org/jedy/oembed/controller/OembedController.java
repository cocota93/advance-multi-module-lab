package org.jedy.oembed.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.oembed_core.service.OembedServiceImpl;
import org.jedy.system_core.global.response.CommonResult;
import org.jedy.system_core.global.response.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/oembed")
@RequiredArgsConstructor
@Slf4j
public class OembedController {

    private final OembedServiceImpl oembedService;
    private final ResponseService responseService;


    @GetMapping(name = "searchGet")
    public String searchPage(){
        return "/oembed/search";
    }

//    @GetMapping(name = "searchPost")
//    @ResponseBody
//    public CommonResult search() throws Exception {
//        //컨텐츠 프로바이더가 누구냐에 따라 응답 컬럼이 조금씩 다르다.
//        //디비를 나눠야할까?
//        //만약 안나눈다면 해당 객체를 감싸는 랩퍼클래스를 타입별로 만들어야할까?
//
//        return responseService.getSuccessResult();
//    }

}
