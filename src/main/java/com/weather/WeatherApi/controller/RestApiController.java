package com.weather.WeatherApi.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;
import com.weather.WeatherApi.beans.WeatherData;
import com.weather.WeatherApi.exceptions.DefaultException;
import com.weather.WeatherApi.service.IWeatherService;
import com.weather.WeatherApi.util.HumidityResponse;
import com.weather.WeatherApi.util.Util;



/**
 * @author sats17,ashu1521,abhimanyu
 *
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	private IWeatherService weatherService;
	
	
	/**
	 * @param city
	 * @return ResponseEntity<SuccessResponse>
	 * @apiNote Use to get live humidity for given city.
	 */
	@GetMapping(value = "/live/humidity")
	public ResponseEntity<HumidityResponse> getLiveHumidityByCity(@RequestParam ("city") String city) {
		HumidityResponse response = weatherService.getLiveHumidity(city);
		return new ResponseEntity<HumidityResponse>(response, HttpStatus.OK);
		
	}
	
	
	/**
	 * @param country
	 * @return Country
	 * @apiNote Use to set country.
	 */
	@PostMapping(value = "/country")
	@ResponseStatus(HttpStatus.CREATED)
	public Country setCountry(@RequestBody final Country country) {
		return weatherService.setCountry(country);
	}
	
	/**
	 * @param city
	 * @return City
	 * @apiNote Use to set city.
	 */
	@PostMapping(value = "/city")
	@ResponseStatus(HttpStatus.CREATED)
	public City setCountry(@RequestBody final City city) {
		City cityObj = null;
		try {
			cityObj = weatherService.setCity(city);
		}
		catch(DataIntegrityViolationException e) {
			throw new DefaultException("City is already present");
		}
		catch(Exception e) {
			throw new DefaultException("Internal server error");
		}
		return cityObj;
	}
	
	/**
	 * @param city
	 * @return String
	 */
	@DeleteMapping(value = "/city")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> deleteCity(@RequestParam(name = "city",required = true) String city) {
		return weatherService.deleteCity(city);	
	}
	
	
	/**
	 * @param weather
	 * @return WeatherData
	 * @apiNote Use to set weather data.
	 */
	@PostMapping(value = "/weather")
	@ResponseStatus(HttpStatus.CREATED)
	public WeatherData setWeather(@RequestBody(required = true) final WeatherData weather) {
		WeatherData obj = null;
		try{
			obj = weatherService.setWeatherData(weather);
		}
		catch(DataIntegrityViolationException e) {
			throw new DefaultException("Data already present");
		}
		return obj;
		
	}
	
	/**
	 * @param city
	 * @return List<WeatherData>
	 * @apiNote Use to get all days weather data for given city.
	 */
	@GetMapping(value = "/weather")
	@ResponseStatus(HttpStatus.OK)
	public List<WeatherData> getWeather(@RequestParam(name = "city",required = true) String city){
		return weatherService.getWeatherByCity(city);
	}
	
	/**
	 * @param city
	 * @param date
	 * @return
	 */
	@DeleteMapping(value = "/weather")
	@ResponseStatus(HttpStatus.OK)
	public HashMap<String, String> deleteWeather(@RequestParam(name = "city",required = true) String city,
			@RequestParam(name = "date",required = true) String date) {
		return weatherService.deleteWeather(city, Util.DateParser(date));	
	}
	
	/**
	 * @param city
	 * @param date
	 * @return
	 */
	@PatchMapping(value = "/weather")
	@ResponseStatus(HttpStatus.OK)
	public WeatherData updateWeather(@RequestParam(name = "city",required = true) String city,@RequestParam(name = "date",required = true) String date,
			@RequestParam(name = "temperature",required = true) Double temperature){
		return weatherService.updateTemperature(city, Util.DateParser(date), temperature);
	}
	
	/**
	 * @param city
	 * @return List<SuccessRespose>
	 * @apiNote Use to get all days humidity for given city.
	 */
	@GetMapping(value = "/weather/humidity")
	public List<HumidityResponse> getHumidityByCity(@RequestParam(name = "city",required = true) String city){
		return weatherService.getHumidityByCity(city);
		
	}
	
	/**
	 * @param city
	 * @param date
	 * @return ResponseEntity<SuccessRespose>
	 * @apiNote Use to get humidity by date and city
	 */
	@GetMapping(value = "/weather/humidity/{city}")
	public ResponseEntity<HumidityResponse> getHumidityByCityAndDate(@PathVariable String city,
			@RequestParam(name = "date" , required = true) String date) {
			HumidityResponse response = weatherService.getHumidity(city,Util.DateParser(date));
			return new ResponseEntity<HumidityResponse>(response, HttpStatus.OK);
		
	}
}	

	
