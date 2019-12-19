package com.weather.WeatherApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.WeatherApi.dao.IWeatherDao;
import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.exceptions.DefaultException;
import com.weather.WeatherApi.util.SuccessRespose;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;


@Service("WeatherService")
public class WeatherServiceImpl implements IWeatherService{

	@Autowired
	private IWeatherDao weatherDao;
	
	@Override
	public SuccessRespose getHumidity(String city) throws CityNotFoundException {
		HttpResponse<JsonNode> httpResponse = weatherDao.getWeather(city);
		
		if(httpResponse.getStatus() == 200) {
			JsonNode body = httpResponse.getBody();
			int humidity = (int) body.getObject().getJSONObject("main").get("humidity");
			SuccessRespose response = new SuccessRespose(city, humidity);
			return response;
		}
		else if(httpResponse.getStatus() == 404){
			throw new CityNotFoundException("Cannot fetch weather for given city.");
		}
		else {
			throw new DefaultException("Something went wrong , please try after some time.");
		}
	}

}
