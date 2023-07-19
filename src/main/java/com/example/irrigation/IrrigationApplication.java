package com.example.irrigation;

import com.example.irrigation.db.entities.Plot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class IrrigationApplication {

    public static void main(String[] args) {
        SpringApplication.run(IrrigationApplication.class, args);

    }
    @Bean
    public Map<Long, Plot> needsIrrigation(){
        return new HashMap<>();
    }


}
