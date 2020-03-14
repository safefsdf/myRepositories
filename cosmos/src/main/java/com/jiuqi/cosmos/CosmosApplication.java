package com.jiuqi.cosmos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.jiuqi.cosmos.dao")
public class CosmosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmosApplication.class, args);
	}

}
