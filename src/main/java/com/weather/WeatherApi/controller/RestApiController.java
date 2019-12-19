package com.weather.WeatherApi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.WeatherApi.beans.WeatherBean;
import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.service.IWeatherService;
import com.weather.WeatherApi.util.SuccessRespose;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

@RestController
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	private IWeatherService weatherService;
	
	
	@GetMapping(value = "/getHumidity")
	public ResponseEntity<SuccessRespose> getHumidityByCity(@RequestParam ("city") String city) {
		SuccessRespose response = weatherService.getHumidity(city);
		return new ResponseEntity<SuccessRespose>(response, HttpStatus.OK);
		
	}
	
}
