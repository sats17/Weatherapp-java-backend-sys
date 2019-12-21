package com.weather.WeatherApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weather.WeatherApi.beans.City;

/**
 * @author sats17
 *
 */
public interface CityRepo extends JpaRepository<City, Integer>{

	/**
	 * @param city
	 * @return City
	 */
	@Query(value = "SELECT c FROM City c WHERE city = :city")
	City getCityByName(String city);
	
}
