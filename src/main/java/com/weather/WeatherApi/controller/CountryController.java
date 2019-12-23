package com.weather.WeatherApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.WeatherApi.service.IWeatherService;

@RestController
@RequestMapping("/api")
public class CountryController {
	
	@Autowired
	private IWeatherService weatherService;
	
}
