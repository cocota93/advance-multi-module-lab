package org.jedy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BootShopAppApiApplication {

//    public static final String APPLICATION_LOCATIONS = "spring.config.location="
//            + "classpath:application.yml,"
////            + "classpath:"
////            + "application.yml,"
//            + "file:/home/ubuntu/application-live.yml";
//
//
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(BootShopAppApiApplication.class)
//                .properties(APPLICATION_LOCATIONS)
//                .run(args);
//    }


    public static void main(String[] args) {
        SpringApplication.run(BootShopAppApiApplication.class, args);
    }
}
