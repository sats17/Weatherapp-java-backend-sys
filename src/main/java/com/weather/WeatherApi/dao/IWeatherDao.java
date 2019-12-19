package com.weather.WeatherApi.dao;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public interface IWeatherDao {
	
	HttpResponse<JsonNode> getWeather(String city);
	
}
