package com.hc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author yusj
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.hc.mapper")
public class MasscanApplication {
	public static void main(String[] args) {
		SpringApplication.run(MasscanApplication.class, args);
	}
}
