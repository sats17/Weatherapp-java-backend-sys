package com.weather.WeatherApi.service;

import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.util.SuccessRespose;

import kong.unirest.JsonNode;

public interface IWeatherService {
	
	SuccessRespose getHumidity(String city) throws CityNotFoundException;
	
}
