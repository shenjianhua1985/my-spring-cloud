package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
		"com.example"})
@EnableZuulProxy
@SpringBootApplication
public class MyZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyZuulApplication.class, args);
	}

}
