package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.Pojo.DepartmentPojo;

@Configuration
public class DepartmentConfiguration {
	
	@Bean
	DepartmentPojo departmentMethod()
	{
		return new DepartmentPojo();
	}
}
