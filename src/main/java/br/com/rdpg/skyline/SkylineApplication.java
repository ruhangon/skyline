package br.com.rdpg.skyline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SkylineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkylineApplication.class, args);
	}

}
