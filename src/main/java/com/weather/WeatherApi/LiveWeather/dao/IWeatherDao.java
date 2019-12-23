package com.weather.WeatherApi.LiveWeather.dao;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

/**
 * @author sats17,ashu1521,abhimanyu
 *
 */
public interface IWeatherDao {
	
	/**
	 * @param city
	 * @return HttpResponse<JsonNode>
	 */
	HttpResponse<JsonNode> getWeather(String city);
	
}
