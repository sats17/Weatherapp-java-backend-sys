package com.weather.WeatherApi.service;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;

import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;
import com.weather.WeatherApi.beans.WeatherData;
import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.util.HumidityResponse;


/**
 * @author sats17,ashu1521,abhimanyu
 *
 */
public interface IWeatherService {
	
	/**
	 * @param city
	 * @return SuccessResponse
	 * @throws CityNotFoundException
	 */
	HumidityResponse getLiveHumidity(String city) throws CityNotFoundException;
	
	/**
	 * @param country
	 * @return Country
	 */
	Country setCountry(Country country);
	
	/**
	 * @param city
	 * @return City
	 * @throws DataIntegrityViolationException
	 */
	City setCity(City city) throws DataIntegrityViolationException;
	
	/**
	 * @param weather
	 * @return WeatherData
	 */
	WeatherData setWeatherData(WeatherData weather);
	
	/**
	 * @param city
	 * @return List<WeatherData>
	 */
	List<WeatherData> getWeatherByCity(String city);
	
	/**
	 * @param city
	 * @return
	 */
	List<HumidityResponse> getHumidityByCity(String city);
	
	/**
	 * @param city
	 * @param date
	 * @return
	 */
	HumidityResponse getHumidity(String city,Date date);
	
	/**
	 * @param city
	 * @return
	 */
	Map<String, String> deleteCity(String city);
	
	/**
	 * @param city
	 * @param date
	 * @return
	 */
	HashMap<String, String> deleteWeather(String city, Date date);
	
	/**
	 * @param city
	 * @param date
	 * @param temperature
	 * @return
	 */
	WeatherData updateTemperature(String city,Date date,Double temperature);
	
	
}
