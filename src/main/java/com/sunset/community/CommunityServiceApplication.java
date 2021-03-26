package com.sunset.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.sunset.community.Mapper","com.sunset.community.Pojo",
//		"com.sunset.community.Service","com.sunset.community.Service.impl","com.sunset.community.Controller"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@MapperScan(value="com.sunset.community.Mapper")
public class CommunityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityServiceApplication.class, args);
	}

}
