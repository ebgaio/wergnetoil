package com.wergnet.wergnetoil.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import com.wergnet.wergnetoil.api.config.property.WergnetOilApiProperty;

//@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(WergnetOilApiProperty.class)
public class WergnetOilApplication {

	public static void main(String[] args) {
		SpringApplication.run(WergnetOilApplication.class, args);
	}

}
