package com.study.weathercheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeathercheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeathercheckApplication.class, args);
	}

}
