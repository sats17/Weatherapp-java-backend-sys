package com.weather.WeatherApi.util;

public class SuccessRespose {
	
	private String city;
	private int humidity;
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
	public int getHumidity() {
		return humidity;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public SuccessRespose(String city, int humidity) {
		super();
		this.city = city;
		this.humidity = humidity;
	}
	
	public SuccessRespose() {}
	
}
