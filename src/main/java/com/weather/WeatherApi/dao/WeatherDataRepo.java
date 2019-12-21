package com.weather.WeatherApi.dao;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weather.WeatherApi.beans.WeatherData;

@Repository
public interface WeatherDataRepo extends JpaRepository<WeatherData, Integer>{

	@Query(value = "SELECT w FROM WeatherData w WHERE w.city.city = :city")
	List<WeatherData> getWeatherByCity(String city);
	
	@Query(value = "SELECT w.date , w.humidity FROM WeatherData w WHERE w.city.city = :city")
	List<Object[]> getHumidityByCity(String city);
//	
	@Query(value = "SELECT w.humidity FROM WeatherData w WHERE w.city.city = :city AND w.date = :date")
	Double getHumidityByCityAndDate(String city,Date date);
//	
//	@Query(value = "SELECT w.temperature FROM WeatherData w WHERE w.date = :date AND w.location.city = :city")
//	Double getTemperatureByCityAndDate(Date date,String city);
	
}
