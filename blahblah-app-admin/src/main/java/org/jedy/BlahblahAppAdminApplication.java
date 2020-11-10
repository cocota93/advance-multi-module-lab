package org.jedy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlahblahAppAdminApplication {

  public static void main(String[] args) {
    SpringApplication.run(BlahblahAppAdminApplication.class, args);
  }

}
