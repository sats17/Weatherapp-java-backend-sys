package com.weather.WeatherApi.exceptions;

public class WeatherNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WeatherNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public WeatherNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WeatherNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public WeatherNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WeatherNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
