package com.weather.WeatherApi.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.weather.WeatherApi.util.SuccessRespose;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;


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
			Double humidity = (Double) body.getObject().getJSONObject("main").get("humidity");
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

	@Override
	public Country setCountry(Country country) {
		return countryDao.save(country);
	}

	@Override
	public City setCity(City city) {
		Country tempCon = countryDao.getCountryByName(city.getCountry().getCountry());
		city.setCountry(tempCon);
		
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
		return weatherDataDao.getWeatherByCity(city);
	}

	@Override
	public SuccessRespose getHumidity(String city, Date date) {
		Double humidity = weatherDataDao.getHumidityByCityAndDate(city, date);
		SuccessRespose response = new SuccessRespose(city,humidity);
		return response;
	}

	@Override
	public Map<Date, Double> getHumidityByCity(String city) {
		// TODO Auto-generated method stub
		List<Object[]> datas =  weatherDataDao.getHumidityByCity(city);
		HashMap<Date, Double> map = new HashMap<Date, Double>();
		for(Object[] data: datas){
	         map.put((Date)data[0],(Double) data[1]);
	     }
		return map;
	}

	

	
	

}
