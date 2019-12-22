package com.weather.WeatherApi.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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



/**
 * @author sats17
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
	@GetMapping(value = "/getLiveHumidity")
	public ResponseEntity<SuccessRespose> getLiveHumidityByCity(@RequestParam ("city") String city) {
		SuccessRespose response = weatherService.getLiveHumidity(city);
		return new ResponseEntity<SuccessRespose>(response, HttpStatus.OK);
		
	}
	
	/**
	 * @param country
	 * @return Country
	 * @apiNote Use to set country.
	 */
	@PostMapping(value = "/setCountry")
	public Country setCountry(@RequestBody final Country country) {
		return weatherService.setCountry(country);
	}
	
	/**
	 * @param city
	 * @return City
	 * @apiNote Use to set city.
	 */
	@PostMapping(value = "/setCity")
	public City setCountry(@RequestBody final City city) {
		return weatherService.setCity(city);
	}
	
	
	/**
	 * @param weather
	 * @return WeatherData
	 * @apiNote Use to set weather data.
	 */
	@PostMapping(value = "/setWeather")
	public WeatherData setWeather(@RequestBody final WeatherData weather) {
		return weatherService.setWeatherData(weather);
	}
	
	/**
	 * @param city
	 * @return List<WeatherData>
	 * @apiNote Use to get all days weather data for given city.
	 */
	@GetMapping(value = "/getWeather")
	public List<WeatherData> getWeather(@RequestParam("city") String city){
		return weatherService.getWeatherByCity(city);
	}
	
	/**
	 * @param city
	 * @return List<SuccessRespose>
	 * @apiNote Use to get all days humidity for given city.
	 */
	@GetMapping(value = "/getHumidity")
	public List<SuccessRespose> getHumidityByCity(@RequestParam("city") String city){
		return weatherService.getHumidityByCity(city);
		
	}
	
	/**
	 * @param city
	 * @param date
	 * @return ResponseEntity<SuccessRespose>
	 * @apiNote Use to get humidity by date and city
	 */
	@GetMapping(value = "/getHumidityByDateAndCity")
	public ResponseEntity<SuccessRespose> getHumidityByCityAndDate(@RequestParam ("city") String city,
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
			System.out.println(response);
			return new ResponseEntity<SuccessRespose>(response, HttpStatus.OK);
		
	}
	
	/**
	 * @param city
	 * @return String
	 */
	@DeleteMapping(value = "/deleteCity")
	public String deleteCity(@RequestParam("city") String city) {
		return weatherService.deleteCity(city);	
	}
	
	@DeleteMapping(value = "/deleteWeather")
	public String deleteWeather(@RequestParam("city") String city,@RequestParam("date") String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = null;
		Date sqlDate = null;
		try {
			parsedDate = dateFormat.parse(date);
		    sqlDate = new Date(parsedDate.getTime());
		} catch (ParseException e) {
			throw new DefaultException("Please enter proper date format");
		}
		return weatherService.deleteWeather(city, sqlDate);	
	}

	
}	
	
