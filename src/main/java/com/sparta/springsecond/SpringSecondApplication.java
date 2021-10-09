package com.sparta.springsecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecondApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
