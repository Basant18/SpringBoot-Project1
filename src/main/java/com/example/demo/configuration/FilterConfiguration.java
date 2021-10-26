//package com.example.demo.configuration;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.filter.DefaultFilter;
//import com.example.demo.filter.TutorialFilter;
//
//@Component
//public class FilterConfiguration {
//	
//	@Bean
//	public FilterRegistrationBean<TutorialFilter> name(){
//		FilterRegistrationBean<TutorialFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//		filterRegistrationBean.setFilter(new TutorialFilter());
//		filterRegistrationBean.addUrlPatterns("/");
//		filterRegistrationBean.setOrder(1);
//		return filterRegistrationBean;
//	}
//	
//	@Bean
//	public FilterRegistrationBean<DefaultFilter> name1(){
//		FilterRegistrationBean<DefaultFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//		filterRegistrationBean.setFilter(new DefaultFilter());
//		filterRegistrationBean.addUrlPatterns("/");
//		filterRegistrationBean.setOrder(0);
//		return filterRegistrationBean;
//	}
//
//}
