package com.shopping.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.shopping.common.entity", "com.shopping.admin.user"})
public class ShoppingBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingBackEndApplication.class, args);
	}

}
