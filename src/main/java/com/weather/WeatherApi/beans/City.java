package com.weather.WeatherApi.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "city",uniqueConstraints=@UniqueConstraint(columnNames={"latitude", "longtitude"}))
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cityid", length = 20)
	private int cityId;
	
	@Column(name = "city",length = 10,unique=true)
	private String city;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="countryid", nullable = false)
	private Country country;
	
	@Column(name = "latitude" , length = 20)
	private double latitude;
	

	@Column(name = "longtitude" , length = 20)
	private double longtitude;


	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}


	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longtitude
	 */
	public double getLongtitude() {
		return longtitude;
	}


	/**
	 * @param longtitude the longtitude to set
	 */
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	
	


	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}


	public City(int cityId, String city, Country country, double latitude, double longtitude) {
		super();
		this.cityId = cityId;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	
	public City() {}


	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + ", country=" + country + ", latitude=" + latitude
				+ ", longtitude=" + longtitude + "]";
	}

	
	
	
	
	
	
	
}
