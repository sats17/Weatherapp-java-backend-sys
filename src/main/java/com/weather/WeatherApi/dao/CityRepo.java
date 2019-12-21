package com.weather.WeatherApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weather.WeatherApi.beans.City;
import com.weather.WeatherApi.beans.Country;

public interface CityRepo extends JpaRepository<City, Integer>{

	@Query(value = "SELECT c FROM City c WHERE city = :city")
	City getCityByName(String city);
	
}
