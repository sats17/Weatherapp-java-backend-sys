package com.weather.WeatherApi.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;
import com.weather.WeatherApi.beans.WeatherData;
import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.util.SuccessRespose;

import kong.unirest.JsonNode;

public interface IWeatherService {
	
	SuccessRespose getLiveHumidity(String city) throws CityNotFoundException;
	
	Country setCountry(Country country);
	
	City setCity(City city);
	
	WeatherData setWeatherData(WeatherData weather);
	
	List<WeatherData> getWeatherByCity(String city);
	
	Map<Date, Double> getHumidityByCity(String city);
	
	SuccessRespose getHumidity(String city,Date date);
	
	
	
}
