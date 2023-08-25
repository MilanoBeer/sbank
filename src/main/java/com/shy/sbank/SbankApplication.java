package com.shy.sbank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Slf4j
public class SbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbankApplication.class, args);
	}

}
