package com.weather.WeatherApi.dao;


import java.sql.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Query(value = "SELECT w FROM WeatherData w WHERE w.city.city = :city AND w.date = :date")
	WeatherData getWeather(String city,Date date);
	
	
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

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM WeatherData w WHERE w.date = :date")
	void deleteWeather(Date date);
}
