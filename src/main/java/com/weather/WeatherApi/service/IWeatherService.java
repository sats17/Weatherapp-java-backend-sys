package com.weather.WeatherApi.service;


import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;
import com.weather.WeatherApi.beans.WeatherData;
import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.util.SuccessRespose;


/**
 * @author sats17
 *
 */
public interface IWeatherService {
	
	SuccessRespose getLiveHumidity(String city) throws CityNotFoundException;
	
	Country setCountry(Country country);
	
	City setCity(City city) throws DataIntegrityViolationException;
	
	WeatherData setWeatherData(WeatherData weather);
	
	List<WeatherData> getWeatherByCity(String city);
	
	List<SuccessRespose> getHumidityByCity(String city);
	
	SuccessRespose getHumidity(String city,Date date);
	
	String deleteCity(String city);
	
	/**
	 * @param city
	 * @param date
	 * @return
	 */
	String deleteWeather(String city, Date date);
	
	WeatherData updateTemperature(String city,Date date,Double temperature);
	
	
}
