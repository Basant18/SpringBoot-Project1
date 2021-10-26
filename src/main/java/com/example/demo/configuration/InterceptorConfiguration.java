//package com.example.demo.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.example.demo.interceptor.EmployeeInterceptor;
//import com.example.demo.interceptor.LoggerInterceptor;
//
//@Configuration
//public class InterceptorConfiguration implements WebMvcConfigurer{
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//	registry.addInterceptor(new LoggerInterceptor()).order(Ordered.LOWEST_PRECEDENCE);
//	registry.addInterceptor(new EmployeeInterceptor()).addPathPatterns("/employee/{empId}")
//	.order(Ordered.HIGHEST_PRECEDENCE);
//	}
//}
