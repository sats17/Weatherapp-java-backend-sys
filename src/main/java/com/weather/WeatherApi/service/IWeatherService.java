package com.weather.WeatherApi.service;


import java.sql.Date;
import java.util.List;

import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;
import com.weather.WeatherApi.beans.WeatherData;
import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.util.SuccessRespose;


public interface IWeatherService {
	
	SuccessRespose getLiveHumidity(String city) throws CityNotFoundException;
	
	Country setCountry(Country country);
	
	City setCity(City city);
	
	WeatherData setWeatherData(WeatherData weather);
	
	List<WeatherData> getWeatherByCity(String city);
	
	List<SuccessRespose> getHumidityByCity(String city);
	
	SuccessRespose getHumidity(String city,Date date);
	
	String deleteCity(String city);
	
	String deleteWeather(String city, Date date);
	
	
}
