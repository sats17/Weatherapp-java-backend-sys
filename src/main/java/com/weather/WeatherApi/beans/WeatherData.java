package com.weather.WeatherApi.beans;


import java.sql.Date;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.weather.WeatherApi.util.Calculation;

/**
 * @author sats17,ashu1521,abhimanyu
 *
 */
@Entity
@Table(name = "weatherdata",uniqueConstraints=@UniqueConstraint(columnNames={"cityid", "date"}))
public class WeatherData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 20)
	private int id;
	
	@Column(name = "temperature")
	private double temperature;
	
	@Column(name = "dew")
	private double dew;
	
	@Column(name = "humidity")
	private double humidity;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="cityid", nullable = false)
	private City city;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the dew
	 */
	public double getDew() {
		return dew;
	}

	/**
	 * @param dew the dew to set
	 */
	public void setDew(double dew) {
		this.dew = dew;
	}

	/**
	 * @return the humidity
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity() {
		this.humidity = Calculation.round(Calculation.calculateRelativeHumidity(this.temperature,this.dew), 2);
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @param id
	 * @param temperature
	 * @param dew
	 * @param humidity
	 * @param date
	 * @param city
	 */
	public WeatherData(int id, double temperature, double dew, double humidity, Date date, City city) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.dew = dew;
		this.humidity = humidity;
		this.date = date;
		this.city = city;
	}

	
	/**
	 * Default constructor
	 */
	public WeatherData() {}
	
	
	
	
	
}
