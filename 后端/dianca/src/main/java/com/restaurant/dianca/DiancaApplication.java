package com.restaurant.dianca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 🚨 这个注解绝对不能少，测试程序就是靠找这个注解来启动环境的
@SpringBootApplication
public class DiancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiancaApplication.class, args);
	}

}