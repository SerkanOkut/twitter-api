package com.twitterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterapiApplication.class, args);
		System.out.println("Uygulama ayakta");
	}

}
