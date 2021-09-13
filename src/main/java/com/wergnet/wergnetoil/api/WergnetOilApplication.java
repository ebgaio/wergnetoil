package com.wergnet.wergnetoil.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.wergnet.wergnetoil.api.config.property.WergnetOilApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(WergnetOilApiProperty.class)
public class WergnetOilApplication {

	public static void main(String[] args) {
		SpringApplication.run(WergnetOilApplication.class, args);
	}

}
