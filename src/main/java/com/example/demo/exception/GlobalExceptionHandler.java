package com.example.demo.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
	public ResponseEntity<?> exception(ResourceNotFoundException ex)
	{
		/*String result = ex.getMessage();
        System.out.println("###########"+result);
        return ex;*/
		ErrorDetails errDetails = new ErrorDetails(new Date(),ex.getMessage());
		return new ResponseEntity<>(errDetails,HttpStatus.NOT_FOUND);
	}
	

}
