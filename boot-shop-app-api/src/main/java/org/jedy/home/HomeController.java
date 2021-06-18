package org.jedy.home;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class HomeController {

//    private PostsService postsService;
    private Environment env;


    @GetMapping(value = {"/", "/home"})
    public String home(){
        return "this is api server_1";
    }

    @GetMapping(value = "/securityCheck")
    public String securityCheck(){
        return "securityCheck";
    }

    @GetMapping("/profile")
    public String getProfile () {
        String[] activeProfiles = env.getActiveProfiles();
        return Arrays.stream(activeProfiles)
                .findFirst()
                .orElse("");
    }
}
