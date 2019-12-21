package com.weather.WeatherApi.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author sats17
 *
 */
@Entity
@Table(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "countryid", length = 20)
	private int countryid;
	
	@Column(name = "country",unique=true)
	private String country;

	/**
	 * @return the countryid
	 */
	public int getCountryid() {
		return countryid;
	}

	/**
	 * @param countryid the countryid to set
	 */
	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param countryid
	 * @param country
	 */
	public Country(int countryid, String country) {
		super();
		this.countryid = countryid;
		this.country = country;
	}
	
	/**
	 * 
	 */
	public Country() {}

	@Override
	public String toString() {
		return "Country [countryid=" + countryid + ", country=" + country + "]";
	}
	
	
	
}
