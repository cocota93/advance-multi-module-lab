package org.jedy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jedy.constant.CustomConstant;
import org.jedy.operator.repository.OperatorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final OperatorRepository operatorRepository;
    private final CustomConstant customConstant;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model){
        model.addAttribute("operatorId", customConstant.getTestOperatorId());
        model.addAttribute("operatorPassword", customConstant.getTestOperatorPassword());
        return "home";
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/corsTest")
    public String corsTest(){
        return "corsTest";
    }

    @GetMapping("/ajaxTest")
    @ResponseBody
    public String ajaxTest(){
        return "ajaxTestSuccess";
    }
}
