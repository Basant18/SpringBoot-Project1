//package com.example.demo.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//public class DefaultFilter extends OncePerRequestFilter{
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFilter.class);
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		LOGGER.info("DefaultFilter filter");
//		String header = request.getHeader("Authorization");
//		System.out.println(header);
//		if(header == null)
//		{
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid authentication");
//			setUnauthorizedResponse(response);
//			return;
//		}
//		else {
//			filterChain.doFilter(request, response);
//		}
//	}
//	
//	public void setUnauthorizedResponse(HttpServletResponse response)throws IOException
//	{
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		response.setContentType("application/json");
//	}
//}
