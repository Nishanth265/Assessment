package com.asessment.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StockService {

		public static void main(String[] args) {
		SpringApplication.run(StockService.class, args);
	}



}
