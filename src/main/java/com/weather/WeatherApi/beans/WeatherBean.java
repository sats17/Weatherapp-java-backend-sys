package com.weather.WeatherApi.beans;

import javax.persistence.Entity;

public class WeatherBean {

	private double temp;
	private double feels_like;
	private double temp_min;
	private double temp_max;
	private int pressure;
	private int humidity;
	private int sea_level;
	private int grnd_level;
	/**
	 * @return the temp
	 */
	public double getTemp() {
		return temp;
	}
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(int temp) {
		this.temp = temp;
	}
	/**
	 * @return the feels_like
	 */
	public double getFeels_like() {
		return feels_like;
	}
	/**
	 * @param feels_like the feels_like to set
	 */
	public void setFeels_like(double feels_like) {
		this.feels_like = feels_like;
	}
	/**
	 * @return the temp_min
	 */
	public double getTemp_min() {
		return temp_min;
	}
	/**
	 * @param temp_min the temp_min to set
	 */
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	/**
	 * @return the temp_max
	 */
	public double getTemp_max() {
		return temp_max;
	}
	/**
	 * @param temp_max the temp_max to set
	 */
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	/**
	 * @return the pressure
	 */
	public int getPressure() {
		return pressure;
	}
	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(int pressure) {
		this.pressure = pressure;
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
	/**
	 * @return the sea_level
	 */
	public int getSea_level() {
		return sea_level;
	}
	/**
	 * @param sea_level the sea_level to set
	 */
	public void setSea_level(int sea_level) {
		this.sea_level = sea_level;
	}
	/**
	 * @return the grnd_level
	 */
	public int getGrnd_level() {
		return grnd_level;
	}
	/**
	 * @param grnd_level the grnd_level to set
	 */
	public void setGrnd_level(int grnd_level) {
		this.grnd_level = grnd_level;
	}
	public WeatherBean(int temp, double feels_like, double temp_min, double temp_max, int pressure, int humidity,
			int sea_level, int grnd_level) {
		super();
		this.temp = temp;
		this.feels_like = feels_like;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.pressure = pressure;
		this.humidity = humidity;
		this.sea_level = sea_level;
		this.grnd_level = grnd_level;
	}
	
	public WeatherBean() {}
	
}
