package com.weather.WeatherApi.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;


@Repository
public class WeatherDaoImpl implements IWeatherDao{
	
	@Value("${api.uri}")
	private String apiPath;
	
	@Value("${api.id}")
	private String apiId;
	
	@Override
	public HttpResponse<JsonNode> getWeather(String city) {
		HttpResponse<JsonNode> response = Unirest.get(apiPath)
                .queryString("APPID",apiId)
                .queryString("q",city)
                .header("content-type","application/json")
                .asJson();
		System.out.println(response.getBody());
		System.out.println(response.getStatus());
		return response;
	}
	
	
	
}
