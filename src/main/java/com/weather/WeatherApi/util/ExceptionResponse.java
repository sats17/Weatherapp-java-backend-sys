package com.weather.WeatherApi.util;

import java.util.Date;

public class ExceptionResponse {

	  private Date timestamp;

	  private String message;

	  private String details;

	  private String httpCodeMessage;

	  /**
	 * @param timestamp
	 * @param message
	 * @param details
	 * @param httpCodeMessage
	 */
	public ExceptionResponse(Date timestamp, String message, String details,String httpCodeMessage) {

	    super();

	    this.timestamp = timestamp;

	    this.message = message;

	    this.details = details;

	    this.httpCodeMessage=httpCodeMessage;

	  }

	/**
	 * @return
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return
	 */
	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}

	/**
	 * @param httpCodeMessage
	 */
	public void setHttpCodeMessage(String httpCodeMessage) {
		this.httpCodeMessage = httpCodeMessage;
	}


	}