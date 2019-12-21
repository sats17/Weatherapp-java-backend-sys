package com.weather.WeatherApi.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;
import com.weather.WeatherApi.beans.WeatherData;
import com.weather.WeatherApi.exceptions.DefaultException;
import com.weather.WeatherApi.service.IWeatherService;
import com.weather.WeatherApi.util.SuccessRespose;



@RestController
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	private IWeatherService weatherService;
	
	
	@GetMapping(value = "/getLiveHumidity")
	public ResponseEntity<SuccessRespose> getLiveHumidityByCity(@RequestParam ("city") String city) {
		SuccessRespose response = weatherService.getLiveHumidity(city);
		return new ResponseEntity<SuccessRespose>(response, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/setCountry")
	public Country setCountry(@RequestBody final Country country) {
		return weatherService.setCountry(country);
	}
	
	@PostMapping(value = "/setCity")
	public City setCountry(@RequestBody final City city) {
		return weatherService.setCity(city);
	}
	
	
	@PostMapping(value = "/setWeather")
	public WeatherData setWeather(@RequestBody final WeatherData weather) {
		return weatherService.setWeatherData(weather);
	}
	
	@GetMapping(value = "/getWeather")
	public List<WeatherData> getWeather(@RequestParam("city") String city){
		return weatherService.getWeatherByCity(city);
	}
	
	@GetMapping(value = "/getHumidity")
	public List<SuccessRespose> getHumidityByCity(@RequestParam("city") String city){
		return weatherService.getHumidityByCity(city);
		
	}
	
	@GetMapping(value = "/getHumidityByDate")
	public ResponseEntity<SuccessRespose> getLiveHumidityByCityAndDate(@RequestParam ("city") String city,
			@RequestParam("date") String date) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate = null;
			Date sqlDate = null;
			try {
				parsedDate = dateFormat.parse(date);
			    sqlDate = new Date(parsedDate.getTime());
			} catch (ParseException e) {
				throw new DefaultException("Please enter proper date format");
			}
		
			SuccessRespose response = weatherService.getHumidity(city,sqlDate);
			return new ResponseEntity<SuccessRespose>(response, HttpStatus.OK);
		
	}

	
}	
	
