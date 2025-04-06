package org.kshrd.gamifiedhabittracker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.kshrd.gamifiedhabittracker.repository")
public class GamifiedHabitTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamifiedHabitTrackerApplication.class, args);
    }

}
