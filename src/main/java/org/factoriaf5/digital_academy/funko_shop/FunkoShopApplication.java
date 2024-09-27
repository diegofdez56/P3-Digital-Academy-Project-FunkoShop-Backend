package org.factoriaf5.digital_academy.funko_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class FunkoShopApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		
		SpringApplication.run(FunkoShopApplication.class, args);
	}

}
