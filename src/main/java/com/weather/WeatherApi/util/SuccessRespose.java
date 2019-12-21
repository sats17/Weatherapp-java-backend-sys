package com.weather.WeatherApi.util;

import java.sql.Date;

public class SuccessRespose {
	
	private String city;
	private Double humidity;
	private Date date;
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the humidity
	 */
	public Double getHumidity() {
		return humidity;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	public SuccessRespose(String city, Double humidity,Date date) {
		super();
		this.city = city;
		this.humidity = humidity;
		this.date = date;
	}
	
	public SuccessRespose() {}
	
}
