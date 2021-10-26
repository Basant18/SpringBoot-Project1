//package com.example.demo.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TutorialFilter implements Filter{
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(TutorialFilter.class);
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		LOGGER.info("TutorialFilter filter");
//		chain.doFilter(request, response);
//		
//	}
//
////	@Override
////	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
////			throws ServletException, IOException {
////		LOGGER.info("TutorialFilter filter");
////		filterChain.doFilter(request, response);
////		
////	}
//	
//	
//}
