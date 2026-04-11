package com.example.WebTra_Springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Lớp khởi chạy chính của ứng dụng Spring Boot.
 * Đây là điểm bắt đầu (EntryPoint) của toàn bộ hệ thống.
 */
@SpringBootApplication
public class WebTraSpringbootApplication {

	public static void main(String[] args) {
		// Khởi động ứng dụng Spring Boot
		SpringApplication.run(WebTraSpringbootApplication.class, args);
	}

}
