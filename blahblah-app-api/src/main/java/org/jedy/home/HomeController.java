package org.jedy.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home(){
        return "this is api server";
    }

    @GetMapping(value = "/securityCheck")
    public String securityCheck(){
        return "securityCheck";
    }


}
