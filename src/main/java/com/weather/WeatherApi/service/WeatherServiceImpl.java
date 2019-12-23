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
import com.weather.WeatherApi.util.Calculation;
import com.weather.WeatherApi.util.GlobalExceptionHandler;
import com.weather.WeatherApi.util.SuccessRespose;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;


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
	public SuccessRespose getLiveHumidity(String city) throws CityNotFoundException {
		HttpResponse<JsonNode> httpResponse = weatherDao.getWeather(city);
		
		if(httpResponse.getStatus() == 200) {
			JsonNode body = httpResponse.getBody();
			Double humidity = ((double)(int) body.getObject().getJSONObject("main").get("humidity"));
			Date time=new Date(body.getObject().getLong("dt")*1000);
			SuccessRespose response = new SuccessRespose(city, humidity,time);
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
	public WeatherData setWeatherData(WeatherData weather) {
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
	public SuccessRespose getHumidity(String city, Date date) {
		Double humidity = weatherDataDao.getHumidityByCityAndDate(city, date);
		if(humidity == null) {
			throw new DefaultException("Humidity not available for given date or city.");
		}
		SuccessRespose response = new SuccessRespose(city,humidity,date);
		return response;
	}

	@Override
	public List<SuccessRespose> getHumidityByCity(String city) {
		// TODO Auto-generated method stub
		List<Object[]> datas =  weatherDataDao.getHumidityByCity(city);
		if(datas.size() == 0) {
			throw new CityNotFoundException("Cannot fetch humidity for given city.");
		}
		List<SuccessRespose> response = new ArrayList<SuccessRespose>();
		for(Object[] data: datas){
	         SuccessRespose obj = new SuccessRespose(city, (Double) data[1],(Date)data[0]);
	         response.add(obj);
	     }
		return response;
	}

	@Override
	public String deleteCity(String city) {
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
		return "City Deleted";
	}

	@Override
	public String deleteWeather(String city, Date date) {
		try {
			WeatherData weatherObj = weatherDataDao.getWeather(city, date);
			weatherDataDao.delete(weatherObj);
		}
		catch(Exception e) {
			throw new DefaultException("Internal server error");
		}
		return "Weather deleted";
	}

	@Override
	public WeatherData updateTemperature(String city, Date date, Double temperature) {
		// TODO Auto-generated method stub
		return null;
	}

}
