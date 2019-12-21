package com.weather.WeatherApi.LiveWeather.dao;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public interface IWeatherDao {
	
	HttpResponse<JsonNode> getWeather(String city);
	
}
