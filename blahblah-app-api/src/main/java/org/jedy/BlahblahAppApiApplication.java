package org.jedy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BlahblahAppApiApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/app/config/blahblah-app-api/real-application.yml";


    public static void main(String[] args) {
        new SpringApplicationBuilder(BlahblahAppApiApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

//
//    public static void main(String[] args) {
//        SpringApplication.run(BlahblahAppApiApplication.class, args);
//    }
}
