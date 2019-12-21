package com.weather.WeatherApi.util;

public class SuccessRespose {
	
	private String city;
	private Double humidity;
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
	public SuccessRespose(String city, Double humidity) {
		super();
		this.city = city;
		this.humidity = humidity;
	}
	
	public SuccessRespose() {}
	
}
