package com.channels.ims.plp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImsPlpServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsPlpServicesApplication.class, args);
	}

}
