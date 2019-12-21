package com.weather.WeatherApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weather.WeatherApi.beans.Country;

/**
 * @author sats17
 *
 */
public interface CountryRepo extends JpaRepository<Country, Integer>{
	
	/**
	 * @param country
	 * @return Country
	 */
	@Query(value = "SELECT c FROM Country c WHERE country = :country")
	Country getCountryByName(String country);
	
}
