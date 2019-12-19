package com.weather.WeatherApi.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.weather.WeatherApi.exceptions.CityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CityNotFoundException.class)
	public static final ResponseEntity<ExceptionResponse> handleGlobalException(CityNotFoundException ex, WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),

				req.getDescription(false), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());

		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

	}
	
//	@ExceptionHandler(InvalidAuthentication.class)
//	public final ResponseEntity<ExceptionResponse> handleInvalidAuthentication(InvalidAuthentication ex, WebRequest req) {
//
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//
//				req.getDescription(false), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//
//		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
//
//	}
//
//	@ExceptionHandler(UnauthorizedException.class)
//	public final ResponseEntity<ExceptionResponse> handleUnathorizedException(UnauthorizedException ex, WebRequest req) {
//
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//
//				req.getDescription(false), HttpStatus.FORBIDDEN.getReasonPhrase());
//
//		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.FORBIDDEN);
//
//	}

}
