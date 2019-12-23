package com.weather.WeatherApi.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

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
import com.weather.WeatherApi.util.SuccessResponse;



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
	public ResponseEntity<SuccessResponse> getLiveHumidityByCity(@RequestParam ("city") String city) {
		SuccessResponse response = weatherService.getLiveHumidity(city);
		return new ResponseEntity<SuccessResponse>(response, HttpStatus.OK);
		
	}
	
	
	/**
	 * @param country
	 * @return Country
	 * @apiNote Use to set country.
	 */
	@PostMapping(value = "/set")
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteCity(@RequestParam(name = "city",required = true) String city) {
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
		return weatherService.setWeatherData(weather);
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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteWeather(@RequestParam(name = "city",required = true) String city,@RequestParam(name = "date",required = true) String date) {
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
	
	/**
	 * @param city
	 * @param date
	 * @return
	 */
	@PatchMapping(value = "/weather")
	public WeatherData updateWeather(@RequestParam(name = "city",required = true) String city,@RequestParam(name = "date",required = true) String date,
			@RequestParam(name = "temperature",required = true) Double temperature){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate = null;
			Date sqlDate = null;
			try {
				parsedDate = dateFormat.parse(date);
				sqlDate = new Date(parsedDate.getTime());
			} catch (ParseException e) {
				throw new DefaultException("Please enter proper date format");
			}
				
		return weatherService.updateTemperature(city, sqlDate, temperature);
	}
	
	/**
	 * @param city
	 * @return List<SuccessRespose>
	 * @apiNote Use to get all days humidity for given city.
	 */
	@GetMapping(value = "/weather/humidity")
	public List<SuccessResponse> getHumidityByCity(@RequestParam(name = "city",required = true) String city){
		return weatherService.getHumidityByCity(city);
		
	}
	
	/**
	 * @param city
	 * @param date
	 * @return ResponseEntity<SuccessRespose>
	 * @apiNote Use to get humidity by date and city
	 */
	@GetMapping(value = "/weather/humidity/{city}")
	public ResponseEntity<SuccessResponse> getHumidityByCityAndDate(@PathVariable String city,
			@RequestParam(name = "date" , required = true) String date) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate = null;
			Date sqlDate = null;
			try {
				parsedDate = dateFormat.parse(date);
			    sqlDate = new Date(parsedDate.getTime());
			} catch (ParseException e) {
				throw new DefaultException("Please enter proper date format");
			}
			SuccessResponse response = weatherService.getHumidity(city,sqlDate);
			return new ResponseEntity<SuccessResponse>(response, HttpStatus.OK);
		
	}

	
}	

	
