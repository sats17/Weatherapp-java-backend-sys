package com.weather.WeatherApi.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weather.WeatherApi.beans.WeatherData;

/**
 * @author sats17
 *
 */
@Repository
public interface WeatherDataRepo extends JpaRepository<WeatherData, Integer>{

	/**
	 * @param city
	 * @return List<WeatherData>
	 */
	@Query(value = "SELECT w FROM WeatherData w WHERE w.city.city = :city")
	List<WeatherData> getWeatherByCity(String city);
	
	/**
	 * @param city
	 * @return List<Object[]>
	 */
	@Query(value = "SELECT w.date , w.humidity FROM WeatherData w WHERE w.city.city = :city")
	List<Object[]> getHumidityByCity(String city);
	
	/**
	 * @param city
	 * @param date
	 * @return Double
	 */
	@Query(value = "SELECT w.humidity FROM WeatherData w WHERE w.city.city = :city AND w.date = :date")
	Double getHumidityByCityAndDate(String city,Date date);

	
}
