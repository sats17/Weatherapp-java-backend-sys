package com.weather.WeatherApi.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.weather.WeatherApi.LiveWeather.dao.IWeatherDao;
import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;
import com.weather.WeatherApi.beans.WeatherData;
import com.weather.WeatherApi.dao.CityRepo;
import com.weather.WeatherApi.dao.CountryRepo;
import com.weather.WeatherApi.dao.WeatherDataRepo;
import com.weather.WeatherApi.exceptions.CityNotFoundException;
import com.weather.WeatherApi.exceptions.DefaultException;
import com.weather.WeatherApi.exceptions.WeatherNotFoundException;
import com.weather.WeatherApi.util.Calculation;
import com.weather.WeatherApi.util.GlobalExceptionHandler;
import com.weather.WeatherApi.util.HumidityResponse;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;


/**
 * @author sats17
 *
 */
@Service("WeatherService")
public class WeatherServiceImpl implements IWeatherService{

	@Autowired
	private IWeatherDao weatherDao;
	
	@Autowired
	private CountryRepo countryDao;
	
	@Autowired
	private WeatherDataRepo weatherDataDao;
	
	@Autowired
	private CityRepo cityDao;
	
	@Override
	public HumidityResponse getLiveHumidity(String city) throws CityNotFoundException {
		HttpResponse<JsonNode> httpResponse = weatherDao.getWeather(city);
		
		if(httpResponse.getStatus() == 200) {
			JsonNode body = httpResponse.getBody();
			Double humidity = ((double)(int) body.getObject().getJSONObject("main").get("humidity"));
			Date time=new Date(body.getObject().getLong("dt")*1000);
			HumidityResponse response = new HumidityResponse(city, humidity,time);
			return response;
		}
		else if(httpResponse.getStatus() == 404){
			throw new CityNotFoundException("Cannot fetch weather for given city.");
		}
		else {
			throw new DefaultException("Something went wrong , please try after some time.");
		}
	}

	@Override
	public Country setCountry(Country country) {
		return countryDao.save(country);
	}

	@Override
	public City setCity(City city) throws DataIntegrityViolationException {
		Country tempCountry = countryDao.getCountryByName(city.getCountry().getCountry());
		city.setCountry(tempCountry);
		
		HttpResponse<JsonNode> httpResponse = weatherDao.getWeather(city.getCity());
		
		if(httpResponse.getStatus() == 200) {
			JsonNode body = httpResponse.getBody();
			Double latitude = ((double) body.getObject().getJSONObject("coord").get("lat"));
			Double longitude = ((double) body.getObject().getJSONObject("coord").get("lon"));
			city.setLatitude(latitude);
			city.setLongitude(longitude);
		}
		else {
			Random rd = new Random();
			Double latRandom = rd.nextDouble();
			Double lonRandom = rd.nextDouble();
			city.setLatitude(Calculation.round(latRandom, 2));
			city.setLongitude(Calculation.round(lonRandom, 2));
		}
		return cityDao.save(city);
	}

	@Override
	public WeatherData setWeatherData(WeatherData weather) throws DataIntegrityViolationException{
		
		if(cityDao.getCityByName(weather.getCity().getCity()) == null) {
			throw new CityNotFoundException("Cannot add weather for given city.");
		}
		City tempCity = cityDao.getCityByName(weather.getCity().getCity());
		weather.setCity(tempCity);
		weather.setHumidity();
		return weatherDataDao.save(weather);
	}

	@Override
	public List<WeatherData> getWeatherByCity(String city) {
		List<WeatherData> data = weatherDataDao.getWeatherByCity(city);
		if(data.size() == 0) {
			throw new CityNotFoundException("Cannot fetch weather for given city.");
		}
		return data;
	}

	@Override
	public HumidityResponse getHumidity(String city, Date date) {
		Double humidity = weatherDataDao.getHumidityByCityAndDate(city, date);
		if(humidity == null) {
			throw new CityNotFoundException("Humidity not available for given date or city.");
		}
		HumidityResponse response = new HumidityResponse(city,humidity,date);
		return response;
	}

	@Override
	public List<HumidityResponse> getHumidityByCity(String city) {
		// TODO Auto-generated method stub
		List<Object[]> datas =  weatherDataDao.getHumidityByCity(city);
		if(datas.size() == 0) {
			throw new CityNotFoundException("Cannot fetch humidity for given city.");
		}
		List<HumidityResponse> response = new ArrayList<HumidityResponse>();
		for(Object[] data: datas){
	         HumidityResponse obj = new HumidityResponse(city, (Double) data[1],(Date)data[0]);
	         response.add(obj);
	     }
		return response;
	}

	@Override
	public Map<String,String> deleteCity(String city) {
		City cityObj = cityDao.getCityByName(city);
		if(cityObj == null) {
			throw new CityNotFoundException("Given city is not found");
		}
		try {
			cityDao.deleteCity(cityObj.getCity());
		}
		catch(Exception e) {
			throw new DefaultException("Internal server error.");
		}
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("Result", "City deleted");
		return result;
	}

	@Override
	public HashMap<String, String> deleteWeather(String city, Date date) {
		WeatherData weatherObj = weatherDataDao.getWeather(city, date);
		if(weatherObj == null) {
			throw new WeatherNotFoundException("Weather not found for given city and date.");
		}
		try {
			
			weatherDataDao.delete(weatherObj);
		}
		catch(Exception e) {
			throw new DefaultException("Internal server error : "+e.getMessage());
		}
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("Result", "Weather deleted");
		return result;
	}

	@Override
	public WeatherData updateTemperature(String city, Date date, Double temperature) throws WeatherNotFoundException {
		WeatherData weatherObj = weatherDataDao.getWeather(city, date);
		if(weatherObj == null) {
			throw new WeatherNotFoundException("Weather not found for given data.");
		}
		weatherObj.setTemperature(temperature);
		weatherObj.setHumidity();
		return weatherDataDao.save(weatherObj);
	}

}
